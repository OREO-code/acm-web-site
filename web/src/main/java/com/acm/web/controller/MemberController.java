package com.acm.web.controller;


import com.acm.web.entity.Member;
import com.acm.web.lang.Result;
import com.acm.web.service.MemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("/members")
    public Result members(@RequestBody Member member){
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("year",member.getYear()).eq("college",member.getCollege());
        List<Member> memberList = memberService.list(queryWrapper);
        if(memberList.size()>0) return Result.success();
        else return Result.fail();
    }

    @PostMapping("/addMember")
    public Result addMember(@RequestBody Member member){
        boolean ans = memberService.save(member);
        if (ans) return Result.success();
        else return Result.fail();
    }

    @PostMapping("/updateMember")
    public Result updateMember(@RequestBody Member member){
        boolean ans = memberService.updateById(member);
        if (ans) return Result.success();
        else return Result.fail();
    }

    @PostMapping("/delMember")
    public Result delMember(@RequestBody Member member){
        boolean ans = memberService.removeById(member.getId());
        if (ans) return Result.success();
        else return Result.fail();
    }
}
