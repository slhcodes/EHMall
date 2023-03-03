package com.example.ehmall.controller;


import com.example.ehmall.entity.Users;
import com.example.ehmall.mapper.UsersMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author slh
 * @since 2023-03-01
 */
@RestController
@Api(tags="用户接口")
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersMapper usersMapper;
@ApiOperation(value="用户",notes="查询用户")
@GetMapping("/select")
    public List<Users> GetUser()
{
    return usersMapper.selectList(null);
}
}

