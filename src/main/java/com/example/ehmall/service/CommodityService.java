package com.example.ehmall.service;

import com.example.ehmall.entity.Commodity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ehmall.entity.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author slh
 * @since 2023-04-21
 */
public interface CommodityService extends IService<Commodity> {
   Commodity getCommodity(int id);

    /**
     * 插入商品
     * @param commodity 商品类
     * @return  是否成功
     */
    public RespBean insertCommodity(Commodity commodity);
}
