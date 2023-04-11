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
}
