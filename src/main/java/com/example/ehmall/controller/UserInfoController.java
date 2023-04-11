package com.example.ehmall.controller;


import com.example.ehmall.entity.PartUserInfo;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.service.impl.UserInfoServiceImpl;
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
 *  用户资料接口
 * </p>
 *
 * @author slh
 * @since 2023-04-08
 */
@RestController
@RequestMapping("/user-info")
@Api(tags="用户资料接口")
public class UserInfoController {

    @Autowired
    private UserInfoServiceImpl userInfoService;
    @ApiOperation(value = "修改头像",notes = "修改头像url,修改成功返回true，失败返回false")
    @GetMapping("/changeimage")
    public RespBean setImage(@ApiParam(name="userId",required = true)
                                 @RequestParam int userId, @ApiParam(name="imageUrl",required = true)
                                 @RequestParam String imageUrl)
    {
       return userInfoService.setImage(userId,imageUrl);
    }
    @ApiOperation(value = "获取头像",notes = "获取头像url,url不为空则成功，否则失败")
    @GetMapping("/getimage")
    public RespBean getImage(@ApiParam(name="userId",required = true)
                             @RequestParam int userId)
    {
        return userInfoService.getImage(userId);
    }
    @ApiOperation(value = "插入用户",notes = "成功插入返回true，失败false")
    @GetMapping("/insertuser")
    public RespBean insertUserInfo(@ApiParam(name="userId",required = true)
                             @RequestParam int userId, @ApiParam(name="userName",required = true)
    @RequestParam String username)
    {
        return userInfoService.insertUser(userId,username);
    }

    @ApiOperation(value = "查询用户id,用户名，头像，个签",notes = "成功返回用户实体，失败返回空")
    @GetMapping("/getpartinfo")
    public PartUserInfo getPartUserInfo(@ApiParam(name="userId",required = true)
                                   @RequestParam int userId)
    {
        return userInfoService.getPartUserInfo(userId);
    }


}

