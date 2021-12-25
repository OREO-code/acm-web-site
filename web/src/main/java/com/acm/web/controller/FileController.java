package com.acm.web.controller;


import com.acm.web.lang.Result;
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
@RequestMapping
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping("/file")
    public Result file(){
        HashMap<String,Object> data = new HashMap<>();
        data.put("sum",fileService.count());
        data.put("fileList",fileService.list());
        return Result.success(data);
    }

    @PostMapping("/addFile")
    public Result addFile(@RequestBody File file){
        file.setCreateTime(LocalDateTime.now());
        file.setUpdateTime(null);
        boolean ans = fileService.save(file);
        if (ans) return Result.success();
        else return Result.fail();
    }

    @PostMapping("/delFile")
    public Result delFile(@RequestBody File file){
        boolean ans = fileService.removeById(file.getId());
        if(ans) return Result.success();
        else return Result.fail(null);
    }

    @PostMapping("/updateFile")
    public Result updateFile(@RequestBody File file){
        boolean ans = fileService.updateById(file);
        if(ans) return Result.success();
        else return Result.fail(null);
    }

}
