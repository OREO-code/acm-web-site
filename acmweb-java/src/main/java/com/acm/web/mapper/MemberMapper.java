package com.acm.web.mapper;

import com.acm.web.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {

    @Select("SELECT year FROM `member` group by year")
    List<String> getYear();

    @Select("SELECT college FROM `member` group by college")
    List<String> getCollege();
}
