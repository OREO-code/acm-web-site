package com.acm.web.service;

import com.acm.web.entity.Time;
import com.acm.web.vo.ResponseVo;
import com.acm.web.vo.TimeVo;
import com.baomidou.mybatisplus.extension.service.IService;


public interface TimeService extends IService<Time> {


    ResponseVo<TimeVo> time();

    ResponseVo updateTime(Time time);
}
