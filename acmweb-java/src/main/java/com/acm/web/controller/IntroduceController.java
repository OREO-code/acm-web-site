package com.acm.web.controller;


import com.acm.web.entity.Introduce;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.service.IntroduceService;
import com.acm.web.vo.ResponseVo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    public ResponseVo<IPage<Introduce>> introduces(@RequestParam(defaultValue = "1") Integer currentPage){
        return introduceService.introduces(currentPage);
    }

    @GetMapping("/introduceAdmin")
    public ResponseVo<List<Introduce>> introduceAdmin(){
        return introduceService.introduceAdmin();
    }

    @GetMapping("/introduce/{id}")
    public ResponseVo<Introduce> detail(@PathVariable(name = "id") Integer id){
        return introduceService.detail(id);
    }

    @PostMapping("/introduce/edit")
    public ResponseVo edit(@RequestBody Introduce introduce){
        return introduceService.edit(introduce);
    }
}
