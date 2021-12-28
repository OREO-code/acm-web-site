package com.acm.web.service.impl;

import com.acm.web.entity.Time;
import com.acm.web.mapper.TimeMapper;
import com.acm.web.service.TimeService;
import com.acm.web.vo.ResponseVo;
import com.acm.web.vo.TimeVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TimeServiceImpl extends ServiceImpl<TimeMapper, Time> implements TimeService {


}
