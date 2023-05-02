package com.example.ehmall.service;

import com.example.ehmall.entity.Comment;
import com.example.ehmall.entity.Pricing;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ehmall.entity.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author slh
 * @since 2023-05-02
 */
public interface PricingService extends IService<Pricing> {
    /**
     * 插入出价
     * @param comment 出价实体
     */
    public RespBean insertPricing(Pricing pricing);

    /**
     * 查询商品的出价
     * @param id 商品id
     * @return  出价列表
     */
    public RespBean getPricing(int id);
}
