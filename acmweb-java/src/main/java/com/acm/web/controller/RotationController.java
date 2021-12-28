package com.acm.web.controller;


import com.acm.web.entity.Rotation;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.service.RotationService;
import com.acm.web.utils.DelFileUtil;
import com.acm.web.utils.UploadUtil;
import com.acm.web.vo.ResponseVo;
import com.acm.web.vo.RotationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        try {
            Rotation rotation1 = rotationService.getById(rotation.getId());
            String rotation1Name = rotation1.getName();
            rotation1.setIsDel(1);
            rotationService.updateById(rotation1);
            return delFileUtil.delFile(rotation1Name,FILEPATH);
        } catch (Exception e) {
            return ResponseVo.error(ResponseEnum.DELETE_ERROR);
        }
    }


    @PostMapping("/addRotation")
    public ResponseVo addRotation(@RequestBody Rotation rotation) {
        boolean ans = rotationService.save(rotation);
        if (ans) return ResponseVo.success("增加成功");
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

    @PostMapping("/uploadRotation")
    public ResponseVo uploadRotation(@RequestParam("file") MultipartFile file) {
        return uploadUtil.upload(file,FILEPATH);
    }
}
