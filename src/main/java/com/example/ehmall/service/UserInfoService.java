package com.example.ehmall.service;

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
     * @return true 修改成功 false 修改失败
     */
    public RespBean setImage(int id, String imageUrl);
    /**
     * 获取用户头像url
     * @return url字符串
     */
    public RespBean getImage(int id);
}
