package com.example.ehmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author slh
 * @since 2023-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Commodity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 商品id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 商品描述
     */
    private String description;

    /**
     * 图片网址,多个网站以英文分号;分割
     */
    private String url1;
    /**
     * 视频网址
     */
    private String url2;
    /**
     * 定价
     */
    private Float price;
    /**
     * 发布时间
     */
    private Date time;
    /**
     * 状态
     */
    private Boolean state;
    /**
     * 分类，多个分类以|分开
     */
    private String category;
    /**
     * 成色
     */
    private String quality;
    /**
     * 购买渠道
     */
    private String purchasechannel;
    /**
     * 原价
     */
    private Float originalprice;
    /**
     * 品牌
     */
    private String brand;


}
