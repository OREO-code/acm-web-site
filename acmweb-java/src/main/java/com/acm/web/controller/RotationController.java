package com.acm.web.controller;


import com.acm.web.entity.Rotation;
import com.acm.web.service.RotationService;
import com.acm.web.vo.ResponseVo;
import com.acm.web.vo.RotationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class RotationController {

    @Autowired
    RotationService rotationService;


    @GetMapping("/rotation")
    public ResponseVo<RotationVo> getRotation() {
        return rotationService.getRotation();
    }

    @PostMapping("/updateRotation")
    public ResponseVo updateRotation(@RequestBody Rotation rotation) {
        return rotationService.updateRotation(rotation);
    }

    @PostMapping("/delRotation")
    public ResponseVo delRotation(@RequestBody Rotation rotation) {
        return rotationService.delRotation(rotation);
    }


    @PostMapping("/addRotation")
    public ResponseVo uploadRotation(@RequestParam("file") MultipartFile file) {
        return rotationService.uploadRotation(file);
    }
}
