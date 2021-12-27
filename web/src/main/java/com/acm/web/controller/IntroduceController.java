package com.acm.web.controller;


import com.acm.web.entity.Introduce;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.service.IntroduceService;
import com.acm.web.vo.IntroduceVo;
import com.acm.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/introduce")
public class IntroduceController {

    @Autowired
    IntroduceService introduceService;

    //TODO 分页
    //TODO 查询失败用try catch捕获+打日志
    @GetMapping("/file")
    public ResponseVo<IntroduceVo> file() {
        IntroduceVo introduceVo = new IntroduceVo()
                .setSum(introduceService.count())
                .setFileList(introduceService.list());
        return ResponseVo.success(introduceVo);
    }

    //TODO 觉得没必要把Introduce直接作为参数传递吧
    //TODO 觉得创建时间和更新时间最好交由数据库管理
    @PostMapping("/addFile")
    public ResponseVo addFile(@RequestBody Introduce introduce) {
        introduce.setCreateTime(LocalDateTime.now());
        introduce.setUpdateTime(LocalDateTime.now());
        boolean ans = introduceService.save(introduce);
        if (ans) return ResponseVo.success("增加成功");
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

    //TODO 没看懂为什么用post
    @PostMapping("/delFile")
    public ResponseVo delFile(@RequestBody Introduce introduce) {
        boolean ans = introduceService.removeById(introduce.getId());
        if (ans) return ResponseVo.success("删除成功");
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

    //TODO 没改更新时间
    //TODO 如果有恶意修改无法进行判断
    @PostMapping("/updateFile")
    public ResponseVo updateFile(@RequestBody Introduce introduce) {
        boolean ans = introduceService.updateById(introduce);
        if (ans) return ResponseVo.success("修改成功");
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

}
