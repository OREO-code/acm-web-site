package com.acm.web.controller;


import com.acm.web.entity.Notice;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.service.NoticeService;
import com.acm.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @GetMapping("/notice")
    public ResponseVo getNotice() {
        Notice notice = noticeService.getById(1);
        if (notice != null) return ResponseVo.success(notice);
        return ResponseVo.error(ResponseEnum.ERROR);
    }

    @PostMapping("/updateNotice")
    public ResponseVo<Notice> updateNotice(@RequestBody Notice notice) {
        return noticeService.updateNotice(notice);
    }

}
