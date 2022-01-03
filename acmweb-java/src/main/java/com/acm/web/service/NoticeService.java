package com.acm.web.service;

import com.acm.web.entity.Notice;
import com.acm.web.vo.ResponseVo;
import com.baomidou.mybatisplus.extension.service.IService;


public interface NoticeService extends IService<Notice> {

    ResponseVo<Notice> updateNotice(Notice notice);

    ResponseVo getNotice();
}
