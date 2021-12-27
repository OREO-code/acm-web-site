package com.acm.web.controller;


import com.acm.web.entity.Member;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.service.MemberService;
import com.acm.web.vo.MemberVo;
import com.acm.web.vo.ResponseVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("/members")
    public ResponseVo<MemberVo> members(@RequestBody Member member){
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("year",member.getYear()).eq("college",member.getCollege());
        MemberVo memberVo = new MemberVo();
        List<Member> memberList = memberService.list(queryWrapper);
        memberVo.setMemberList(memberList);
        if(memberList.size()>0) return ResponseVo.success(memberVo);
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

    @PostMapping("/addMember")
    public ResponseVo addMember(@RequestBody Member member){
        boolean ans = memberService.save(member);
        if (ans) return ResponseVo.success("增加成功");
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

    @PostMapping("/updateMember")
    public ResponseVo updateMember(@RequestBody Member member){
        boolean ans = memberService.updateById(member);
        if (ans) return ResponseVo.success("修改成功");
        else return ResponseVo.error(ResponseEnum.ERROR);
    }

    @PostMapping("/delMember")
    public ResponseVo delMember(@RequestBody Member member){
        boolean ans = memberService.removeById(member.getId());
        if (ans) return ResponseVo.success("删除成功");
        else return ResponseVo.error(ResponseEnum.ERROR);
    }
}
