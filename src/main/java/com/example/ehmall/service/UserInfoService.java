package com.example.ehmall.service;

import com.example.ehmall.entity.PartUserInfo;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author slh
 * @since 2023-04-08
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 修改用户头像url
     * @param id
     * @param imageUrl
     * @return true 修改成功 false 修改失败
     */
    public RespBean setImage(int id, String imageUrl);
    /**
     * 获取用户头像url
     * @param id
     * @return url字符串
     */
    public RespBean getImage(int id);
    /**
     * 修改用户头像url
     * @param id
     * @param username
     * @time 2023/4/12
     * @return true 修改成功 false 修改失败
     */
    public RespBean setUsername(int id, String username);

    /**
     * 修改性别
     * @param id
     * @param gender
     * @time 2023/4/25
     * @return
     */
    public RespBean setGender(int id,Boolean gender);
    /**
     * 修改个签
     * @param id
     * @param signature
     * @time 2023/4/25
     * @return
     */
    public RespBean setSignature(int id,String signature);
    /**
     * 插入用户id和用户名
     * @param id
     * @param userName
     */
    public RespBean insertUser(int id, String userName);

    /**
     * 查询用户id,username,signature,imageurl,
     * @param userId
     * @return
     */
    public PartUserInfo getPartUserInfo(int userId);
    /**
     * 删除redis中的用户信息
     * @param userId
     */
    public String deletePartUserInfo(int userId);
}
