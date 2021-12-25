package com.acm.web.controller;


import com.acm.web.entity.Introduce;
import com.acm.web.lang.Result;
import com.acm.web.service.IntroduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
@RequestMapping("/introduce")
public class IntroduceController {
    @Autowired
    IntroduceService introduceService;

    @GetMapping("/file")
    public Result file(){
        HashMap<String,Object> data = new HashMap<>();
        data.put("sum",introduceService.count());
        data.put("fileList",introduceService.list());
        return Result.success(data);
    }

    @PostMapping("/addFile")
    public Result addFile(@RequestBody Introduce introduce){
        introduce.setCreateTime(LocalDateTime.now());
        introduce.setUpdateTime(null);
        boolean ans = introduceService.save(introduce);
        if (ans) return Result.success();
        else return Result.fail();
    }

    @PostMapping("/delFile")
    public Result delFile(@RequestBody Introduce introduce){
        boolean ans = introduceService.removeById(introduce.getId());
        if(ans) return Result.success();
        else return Result.fail(null);
    }

    @PostMapping("/updateFile")
    public Result updateFile(@RequestBody Introduce introduce){
        boolean ans = introduceService.updateById(introduce);
        if(ans) return Result.success();
        else return Result.fail(null);
    }

}
