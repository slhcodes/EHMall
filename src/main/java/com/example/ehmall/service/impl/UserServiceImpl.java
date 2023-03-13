package com.example.ehmall.service.impl;

import com.example.ehmall.entity.User;
import com.example.ehmall.mapper.UserMapper;
import com.example.ehmall.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author slh
 * @since 2023-03-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
