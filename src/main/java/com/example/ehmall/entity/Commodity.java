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
 * 商品信息实体
 *
 * @author slh
 * @since 2023-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)

public class Commodity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String description;

    /**
     * 图片网址
     */
    private String url1;

    /**
     * 视频网址
     */
    private String url2;

    private Float price;

    private Date time;

    private Boolean state;

    /**
     * 分类
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


}
