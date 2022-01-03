package com.acm.web.service.impl;

import cn.hutool.core.io.FileTypeUtil;
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

import java.io.IOException;
import java.io.InputStream;


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

    //TODO 暂时无法判断svg
    @Override
    public ResponseVo uploadRotation(MultipartFile file) {
        String id = String.valueOf(IdUtil.nextId());
        String path;
        try {
            InputStream inputStream = file.getInputStream();
            String type = FileTypeUtil.getType(inputStream, file.getOriginalFilename());
            if (!type.equals("jpg") && !type.equals("png")) {
                return ResponseVo.error(ResponseEnum.UPLOAD_TYPE_ILLEGAL);
            }
            path = uploadUtil.upload(file, FILEPATH, id + "." + type);
            Rotation rotation = new Rotation()
                    .setUrl(path)
                    .setIsDel(0)
                    .setName(file.getOriginalFilename());
            this.save(rotation);
        } catch (IOException e) {
            return ResponseVo.error(ResponseEnum.UPLOAD_ERROR);
        }
        return ResponseVo.success(new FileVo().setUrl(path));
    }
}
