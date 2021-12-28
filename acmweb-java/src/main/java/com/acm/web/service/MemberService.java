package com.acm.web.service;

import com.acm.web.entity.Member;
import com.acm.web.form.QueryMembers;
import com.acm.web.vo.MemberVo;
import com.acm.web.vo.ResponseVo;
import com.baomidou.mybatisplus.extension.service.IService;


public interface MemberService extends IService<Member> {



    ResponseVo<MemberVo> members(QueryMembers queryMembers);
}
