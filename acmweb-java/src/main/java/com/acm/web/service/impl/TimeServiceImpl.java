package com.acm.web.service.impl;

import com.acm.web.entity.Time;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.mapper.TimeMapper;
import com.acm.web.service.TimeService;
import com.acm.web.vo.ResponseVo;
import com.acm.web.vo.TimeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TimeServiceImpl extends ServiceImpl<TimeMapper, Time> implements TimeService {

    Time time = new Time();

    @Autowired
    TimeMapper timeMapper;

    @Override
    public ResponseVo<TimeVo> time() {
        TimeVo timeVo = new TimeVo()
                .setSum(time.selectCount(null))
                .setTimeList(time.selectList(new QueryWrapper<Time>().orderByAsc("time")));
        if (timeVo.getTimeList().size() > 0) return ResponseVo.success(timeVo);
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

    @Override
    public ResponseVo updateTime(Time time) {
        boolean ans = this.updateById(time);
        if(ans) return ResponseVo.success("");
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

    @Override
    public ResponseVo delTime(Integer id) {
        boolean ans = this.removeById(id);
        return null;
    }

    @Override
    public ResponseVo addTime(Time time) {
        return null;
    }
}
