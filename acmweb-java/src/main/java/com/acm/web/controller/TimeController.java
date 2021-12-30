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

    @GetMapping("/delTime")
    public ResponseVo delTime(@RequestParam Integer id) {
        return timeService.delTime(id);
    }

    @PostMapping("/addTime")
    public ResponseVo addTime(@RequestBody Time time) {
        return timeService.addTime(time);
    }
}
