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
 * @since 2023-05-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Evaluation implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    @TableId(value = "userid", type = IdType.ID_WORKER_STR)
    private Integer userid;
    /**
     * 订单id
     */
    private Integer commerid;
    /**
     * 评价内容
     */
    private String content;
    /**
     * 时间
     */
    private Date time;
    /**
     * 1好评 2中评 3差评
     */
    private Integer score;
    /**
     * 0未评价，1已评价可追评 ，2已评价不可追评
     */
    private Integer state;


}
