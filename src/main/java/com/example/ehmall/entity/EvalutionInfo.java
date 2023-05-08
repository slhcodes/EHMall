package com.example.ehmall.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 评价接口实体
 * @author 施立豪
 * @time 2023/5/8
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EvalutionInfo {
    /**
     * 评价信息
     */
    private Evaluation evaluation;
    /**
     * 订单信息
     */
    private Commerce commerce;
    /**
     * 商品信息
     */
    private Commodity commodity;
}
