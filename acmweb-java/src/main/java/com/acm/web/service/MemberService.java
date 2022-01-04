package com.acm.web.service;

import com.acm.web.entity.Member;
import com.acm.web.form.QueryMembers;
import com.acm.web.vo.ResponseVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface MemberService extends IService<Member> {

    ResponseVo<List<Member>> members(QueryMembers queryMembers);

    ResponseVo<List<String>> getYear();

    ResponseVo<List<String>> getCollege();

    ResponseVo addMember(Member member);

    ResponseVo updateMember(Member member);

    ResponseVo delMember(Integer id);

    ResponseVo<String> addMember(MultipartFile file);

    ResponseVo<List<Member>> getAll();
}
