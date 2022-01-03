package com.acm.web.service.impl;

import com.acm.web.entity.Notice;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.mapper.NoticeMapper;
import com.acm.web.service.NoticeService;
import com.acm.web.vo.ResponseVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public ResponseVo<Notice> updateNotice(Notice notice) {
        notice.setId(1);
        int i = noticeMapper.updateById(notice);
        if (i != 0) return ResponseVo.success();
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

    @Override
    public ResponseVo getNotice() {
        Notice notice = this.getById(1);
        if (notice != null) return ResponseVo.success(notice);
        return ResponseVo.error(ResponseEnum.ERROR);
    }
}
