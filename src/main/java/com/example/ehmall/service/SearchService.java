package com.example.ehmall.service;

import com.example.ehmall.entity.Commodity;
import com.example.ehmall.entity.PartUserInfo;

import java.io.IOException;
import java.util.List;

/**
 * 搜索服务类
 * @author 施立豪
 * @time 2023/5/1
 */
public interface SearchService {
    /**
     * 搜索用户名
     * @param userName 用户名
     * @return  用户列表
     */
    public List<PartUserInfo> searchUser(String userName) throws IOException;

    /**
     * 搜索商品
     * @param comName  商品描述
     * @return   商品列表
     */
    public List<Commodity> searchCommodity(String comName) throws IOException;

}
