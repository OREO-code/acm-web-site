package com.acm.web.service.impl;

import com.acm.web.entity.Member;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.form.QueryMembers;
import com.acm.web.mapper.MemberMapper;
import com.acm.web.service.MemberService;
import com.acm.web.vo.MemberVo;
import com.acm.web.vo.ResponseVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {


    @Override
    public ResponseVo<MemberVo> members(QueryMembers queryMembers) {
        Member member = new Member();
        MemberVo memberVo = new MemberVo();
        List<String> yearList = queryMembers.getYearList();
        List<String> collegeList = queryMembers.getCollegeList();
        List<Member> memberList = new ManagedList<>();
        if(yearList==null||collegeList==null){
            return ResponseVo.error(ResponseEnum.ERROR_PARAM);
        }
        else if(yearList.size()==0&&collegeList.size()==0){
            return ResponseVo.error(ResponseEnum.NEED_PARAM);
        }
        else if(collegeList.size()>0 && yearList.size()==0){
            for(String college:collegeList){
                QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("college",college);
                List<Member> members = member.selectList(queryWrapper);
                memberList.addAll(members);
            }
        }
        else if(collegeList.size() == 0){
            for(String year:yearList){
                QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("year",year);
                List<Member> members = member.selectList(queryWrapper);
                memberList.addAll(members);
            }
        }
        else {
            for (String year:yearList){
                for(String college:collegeList){
                    QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("year", year).eq("college", college);
                    List<Member> members = member.selectList(queryWrapper);
                    memberList.addAll(members);
                }
            }

        }
        memberVo.setMemberList(memberList);
        return ResponseVo.success(memberVo);
    }
}
