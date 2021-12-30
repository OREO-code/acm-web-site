package com.acm.web.controller;


import com.acm.web.entity.Introduce;
import com.acm.web.service.IntroduceService;
import com.acm.web.vo.ResponseVo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author henrik
 * @since 2021-12-29
 */
@RestController
public class IntroduceController {

    @Autowired
    IntroduceService introduceService;

    @GetMapping("/introduces")
    public ResponseVo<IPage> introduces(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage,5);
        IPage pageDate = introduceService.page(page, new QueryWrapper<Introduce>().orderByDesc("created"));
        return ResponseVo.success(pageDate);
    }

}
