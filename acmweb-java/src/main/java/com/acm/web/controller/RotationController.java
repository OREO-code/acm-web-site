package com.acm.web.controller;


import com.acm.web.entity.Rotation;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.service.RotationService;
import com.acm.web.utils.DelFileUtil;
import com.acm.web.utils.IdUtil;
import com.acm.web.utils.UploadUtil;
import com.acm.web.vo.FileVo;
import com.acm.web.vo.ResponseVo;
import com.acm.web.vo.RotationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping
public class RotationController {

    @Autowired
    RotationService rotationService;

    @Autowired
    UploadUtil uploadUtil;

    @Autowired
    DelFileUtil delFileUtil;

    private static final String FILEPATH = "rotation/";


    @GetMapping("/rotation")
    public ResponseVo<RotationVo> getRotation() {
        RotationVo rotationVo = new RotationVo()
                .setSum(rotationService.count())
                .setUrlList(rotationService.list());
        return ResponseVo.success(rotationVo);
    }

    @PostMapping("/updateRotation")
    public ResponseVo updateRotation(@RequestBody Rotation rotation) {
        boolean ans = rotationService.updateById(rotation);
        if (ans) return ResponseVo.success("修改成功");
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

    @PostMapping("/delRotation")
    public ResponseVo delRotation(@RequestBody Rotation rotation) {
        String rotation1Name;
        try {
            Rotation rotation1 = rotationService.getById(rotation.getId());
            rotation1Name = rotation1.getName();
            rotation1.setIsDel(1);
            rotationService.updateById(rotation1);
        } catch (Exception e) {
            return ResponseVo.error(ResponseEnum.DELETE_ERROR);
        }
        boolean flag = delFileUtil.delFile(rotation1Name, FILEPATH);
        if (flag) return ResponseVo.success("删除成功");
        return ResponseVo.error(ResponseEnum.DELETE_ERROR);
    }


    @PostMapping("/addRotation")
    public ResponseVo addRotation(@RequestBody Rotation rotation) {
        boolean ans = rotationService.save(rotation);
        if (ans) return ResponseVo.success("增加成功");
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

    @PostMapping("/uploadRotation")
    public ResponseVo uploadRotation(@RequestParam("file") MultipartFile file) {
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
                .setName(id + split[1]);
        try {
            rotationService.save(rotation);
        } catch (Exception e) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success(new FileVo().setUrl(path));
    }
}
