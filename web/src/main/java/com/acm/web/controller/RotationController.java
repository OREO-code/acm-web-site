package com.acm.web.controller;


import com.acm.web.entity.Rotation;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.service.RotationService;
import com.acm.web.vo.ResponseVo;
import com.acm.web.vo.RotationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class RotationController {

    @Autowired
    RotationService rotationService;

    @GetMapping("/rotation")
    public ResponseVo<RotationVo> getRotation(){
        RotationVo rotationVo = new RotationVo()
                .setSum(rotationService.count())
                .setUrlList(rotationService.list());
        return ResponseVo.success(rotationVo);
    }

    @PostMapping("/updateRotation")
    public ResponseVo updateRotation(@RequestBody Rotation rotation){
        boolean ans = rotationService.updateById(rotation);
        if(ans) return ResponseVo.success();
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

    @PostMapping("/delRotation")
    public ResponseVo delRotation(@RequestBody Rotation rotation){
        boolean ans = rotationService.removeById(rotation.getId());
        if(ans) return ResponseVo.success();
        else return ResponseVo.error(ResponseEnum.ERROR);
    }


    @PostMapping("/addRotation")
    public ResponseVo addRotation(@RequestBody Rotation rotation){
        boolean ans = rotationService.save(rotation);
        if (ans) return ResponseVo.success();
        else return ResponseVo.error(ResponseEnum.ERROR);
    }
}
