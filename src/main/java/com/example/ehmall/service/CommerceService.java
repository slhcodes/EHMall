package com.example.ehmall.service;

import com.example.ehmall.entity.Commerce;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ehmall.entity.Pricing;
import com.example.ehmall.entity.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author slh
 * @since 2023-05-02
 */
public interface CommerceService extends IService<Commerce> {
    /**
     * 插入交易
     * @param commerce 交易实体
     */
    public RespBean insertCommerce(Commerce commerce);
    /**
     * 更新交易状态
     */
    public RespBean updateState(int commerceId,int state);
    /**
     * 查询交易状态
     */
    public Commerce getCommerce(int commodityid,int sellerid,int buyerid);
}
