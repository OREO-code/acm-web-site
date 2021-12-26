package com.acm.web.mapper;

import com.acm.web.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectByUsername(String username);

    boolean addUser(User user);
}

