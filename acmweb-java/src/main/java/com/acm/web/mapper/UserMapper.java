package com.acm.web.mapper;

import com.acm.web.entity.Qrcode;
import com.acm.web.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(String username);

    boolean addUser(User user);

    boolean updateUser(User user);
}

