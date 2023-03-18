package com.example.ehmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.ehmall.entity.User;
import com.example.ehmall.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author slh
 * @since 2023-03-13
 */
@RestController
@RequestMapping("/user")
@Api(tags="用户账号接口")
public class UserController
{
    /**
     * 手机号登录的用户，插入至数据库
     * @param phone 手机号
     * @return 是否成功插入
     * @author 施立豪
     * @time 2023/3/18
     */
    @Autowired
    private UserMapper userMapper;
    @ApiOperation(value = "手机号登录添加用户",notes = "添加用户")
    @GetMapping("/insertuserbyphone")
    public boolean InsertUserByPhone(@ApiParam(name="phone",required = true)
                        @RequestParam String phone)
    {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.eq(User::getPhone, phone);
        User loginUser = userMapper.selectOne(lqw);
        if(loginUser==null){
        User user = new User();
        user.setPhone(phone);
        user.setState(true);
        int result=userMapper.insert(user);
        return result==1;}else return true;
    }

    /**
     * 封掉特点手机号的用户账户
     * @param phone 手机号
     * @return  是否正确封禁
     * @author 施立豪
     * @time 2023/3/18
     */
    @ApiOperation(value = "根据手机号封禁用户",notes = "封禁用户")
    @GetMapping("/banuserbyphone")
    public boolean BanUserByPhone(@ApiParam(name="phone",required = true)
                        @RequestParam String phone)
    {
        /**
         * 查询到被封手机号用户的实体
         * 只更新一个属性，账号状态设置为0-表示被封
         */
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("phone",phone).set("state", 0);
        int result=userMapper.update(null, updateWrapper);
        return result==1;
    }


}

