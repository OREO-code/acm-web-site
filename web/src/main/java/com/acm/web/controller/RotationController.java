package com.acm.web.controller;


import com.acm.web.entity.Rotation;
import com.acm.web.lang.Result;
import com.acm.web.service.RotationService;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author henrik
 * @since 2021-12-25
 */
@RestController
@RequestMapping
public class RotationController {

    @Autowired
    RotationService rotationService;

    @GetMapping("/rotation")
    public Result getRotation(){
        HashMap<String,Object> data = new HashMap<>();
        data.put("pageNumber",rotationService.count());
        data.put("urlList",rotationService.list());
        return Result.success(data);
    }

    @PostMapping("/updateRotation")
    public Result updateRotation(@RequestBody Rotation rotation){
        boolean ans = rotationService.updateById(rotation);
        if(ans) return Result.success();
        else return Result.fail();
    }

    @PostMapping("/delRotation")
    public Result delRotation(@RequestBody Rotation rotation){
        boolean ans = rotationService.removeById(rotation.getId());
        if(ans) return Result.success();
        else return Result.fail();
    }


    @PostMapping("/addRotation")
    public Result addRotation(@RequestBody Rotation rotation){
        boolean ans = rotationService.save(rotation);
        if (ans) return Result.success();
        else return Result.fail();
    }
}
