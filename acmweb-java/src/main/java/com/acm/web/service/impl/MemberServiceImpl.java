package com.acm.web.service.impl;

import com.acm.web.entity.Member;
import com.acm.web.mapper.MemberMapper;
import com.acm.web.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

}
