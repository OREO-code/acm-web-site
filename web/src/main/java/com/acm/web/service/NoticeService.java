package com.acm.web.service;

import com.acm.web.entity.Notice;
import com.acm.web.lang.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author henrik
 * @since 2021-12-25
 */
public interface NoticeService extends IService<Notice> {

    Result updateNotice(Notice notice);
}
