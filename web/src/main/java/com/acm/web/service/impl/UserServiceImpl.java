package com.acm.web.service.impl;

import com.acm.web.entity.User;
import com.acm.web.mapper.UserMapper;
import com.acm.web.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author henrik
 * @since 2021-12-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
