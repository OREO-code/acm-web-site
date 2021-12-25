package com.acm.web.controller;


import com.acm.web.entity.Time;
import com.acm.web.lang.Result;
import com.acm.web.service.TimeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
public class TimeController {

    @Autowired
    TimeService timeService;

    @PostMapping("/time")
    public Result time(@RequestBody Time time){
        List<Time> timeList = timeService.list(new QueryWrapper<Time>().eq("year", time.getYear()));
        if(timeList.size()>0) return Result.success(timeList);
        else return Result.fail();
    }

    @PostMapping("/updateTime")
    public Result updateTime(@RequestBody Time time){
        boolean ans = timeService.updateById(time);
        if (ans) return Result.success();
        else return Result.fail();
    }

    @PostMapping("/delTime")
    public Result delTime(@RequestBody Time time){
        boolean ans = timeService.removeById(time.getId());
        if (ans) return Result.success();
        else return Result.fail();
    }

    @PostMapping("/addTime")
    public Result addTime(@RequestBody Time time){
        boolean ans = timeService.save(time);
        if (ans) return Result.success();
        else return Result.fail();
    }
}
