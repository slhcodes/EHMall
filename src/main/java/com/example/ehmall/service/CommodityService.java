package com.example.ehmall.service;

import com.example.ehmall.entity.Commodity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ehmall.entity.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author slh
 * @since 2023-04-21
 */
public interface CommodityService extends IService<Commodity> {
    /**
     * 获取商品信息
     * @param id 商品id
     * @return
     */
   Commodity getCommodity(int id);

    /**
     * 插入商品
     * @param commodity 商品类
     * @return  是否成功
     */
    public RespBean insertCommodity(Commodity commodity);
    /**
     * 获取关注商品
     * @param  users 关注列表用户ID
     * @param page 页数
     */
    public List<Commodity> getFocusedCommodity(int[] users, int page);

    /**
     * 获取我的商品
     * @param user
     * @return
     */
    public List<Commodity> getFocusedCommodity(int userid);

}
