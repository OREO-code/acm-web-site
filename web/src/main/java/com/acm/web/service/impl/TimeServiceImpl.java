package com.acm.web.service.impl;

import com.acm.web.entity.Time;
import com.acm.web.lang.Result;
import com.acm.web.mapper.TimeMapper;
import com.acm.web.service.TimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author henrik
 * @since 2021-12-25
 */
@Service
public class TimeServiceImpl extends ServiceImpl<TimeMapper, Time> implements TimeService {


}
