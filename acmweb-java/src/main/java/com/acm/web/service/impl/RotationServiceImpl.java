package com.acm.web.service.impl;

import com.acm.web.entity.Rotation;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.mapper.RotationMapper;
import com.acm.web.service.RotationService;
import com.acm.web.utils.DelFileUtil;
import com.acm.web.utils.IdUtil;
import com.acm.web.utils.UploadUtil;
import com.acm.web.vo.FileVo;
import com.acm.web.vo.ResponseVo;
import com.acm.web.vo.RotationVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;


@Service
public class RotationServiceImpl extends ServiceImpl<RotationMapper, Rotation> implements RotationService {


    @Autowired
    UploadUtil uploadUtil;

    @Autowired
    DelFileUtil delFileUtil;

    private static final String FILEPATH = "image/";

    @Override
    public ResponseVo<RotationVo> getRotation() {
        QueryWrapper<Rotation> wrapper = new QueryWrapper<>();
        wrapper.eq("isDel", 0);
        RotationVo rotationVo = new RotationVo()
                .setSum(this.count())
                .setUrlList(this.list(wrapper));
        return ResponseVo.success(rotationVo);
    }

    @Override
    public ResponseVo updateRotation(Rotation rotation) {
        boolean ans = this.updateById(rotation);
        if (ans) return ResponseVo.success("修改成功");
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

    @Override
    public ResponseVo delRotation(Rotation rotation) {
        String rotation1Name;
        try {
            Rotation rotation1 = this.getById(rotation.getId());
            rotation1Name = rotation1.getName();
            rotation1.setIsDel(1);
            this.updateById(rotation1);
        } catch (Exception e) {
            return ResponseVo.error(ResponseEnum.DELETE_ERROR);
        }
        boolean flag = delFileUtil.delFile(rotation1Name, FILEPATH);
        if (flag) return ResponseVo.success("删除成功");
        return ResponseVo.error(ResponseEnum.DELETE_ERROR);
    }

    @Override
    public ResponseVo uploadRotation(MultipartFile file) {
        String id = String.valueOf(IdUtil.nextId());
        String[] split = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        //TODO 询问业务逻辑
        //TODO 待完善,当前只能通过后缀名判断
        if (!split[1].equals("jpg") && !split[1].equals("svg") && !split[1].equals("png")) {
            return ResponseVo.error(ResponseEnum.UPLOAD_TYPE_ILLEGAL);
        }
        String path = uploadUtil.upload(file, FILEPATH, id + "." + split[1]);
        Rotation rotation = new Rotation()
                .setUrl(path)
                .setIsDel(0)
                .setName(file.getOriginalFilename());
        try {
            this.save(rotation);
        } catch (Exception e) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success(new FileVo().setUrl(path));
    }
}
