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
    public ResponseVo time() {
        List<Time> timeList = timeService.list();
        TimeVo timeVo = new TimeVo()
                .setSum(timeService.count())
                .setTimeList(timeList);
        if (timeList.size() > 0) return ResponseVo.success(timeVo);
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

    @PostMapping("/updateTime")
    public ResponseVo updateTime(@RequestBody Time time) {
        boolean ans = timeService.updateById(time);
        if (ans) return ResponseVo.success("修改成功");
        else return ResponseVo.error(ResponseEnum.ERROR);
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
