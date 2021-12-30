package com.acm.web.controller;


import com.acm.web.entity.Time;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.service.TimeService;
import com.acm.web.vo.ResponseVo;
import com.acm.web.vo.TimeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
public class TimeController {

    @Autowired
    TimeService timeService;

    @GetMapping("/time")
    public ResponseVo<TimeVo> time() {
        return timeService.time();
    }

    @PostMapping("/updateTime")
    public ResponseVo updateTime(@RequestBody Time time) {
        return timeService.updateTime(time);
    }

    @PostMapping("/delTime")
    public ResponseVo delTime(@RequestBody Time time) {
        boolean ans = timeService.removeById(time.getId());
        if (ans) return ResponseVo.success("删除成功");
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

    @PostMapping("/addTime")
    public ResponseVo addTime(@RequestBody Time time) {
        boolean ans = timeService.save(time);
        if (ans) return ResponseVo.success("增加成功");
        else return ResponseVo.error(ResponseEnum.ERROR);
    }
}
