package com.acm.web.controller;


import com.acm.web.entity.Notice;
import com.acm.web.lang.Result;
import com.acm.web.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author henrik
 * @since 2021-12-25
 */
@RestController
@RequestMapping
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @GetMapping("/notice")
    public Result getNotice(){
        Notice notice = noticeService.getById(1);
        if(notice!=null) return Result.success(notice);
        return Result.fail();
    }

    @PostMapping("/updateNotice")
    public Result updateNotice(@RequestBody Notice notice){
        return noticeService.updateNotice(notice);
    }

}
