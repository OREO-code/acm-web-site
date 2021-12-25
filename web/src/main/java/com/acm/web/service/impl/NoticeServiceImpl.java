package com.acm.web.service.impl;

import com.acm.web.entity.Notice;
import com.acm.web.lang.Result;
import com.acm.web.mapper.NoticeMapper;
import com.acm.web.service.NoticeService;
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
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public Result updateNotice(Notice notice) {
        notice.setId(1);
        int i = noticeMapper.updateById(notice);
        if(i!=0) return Result.success();
        else return Result.fail();
    }
}
