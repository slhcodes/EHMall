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
 * @since 2023-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Commerce implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer commodityid;

    private Integer sellerid;

    private Integer buyerid;

    private String place;

    private Date time;

    private Double price;

    /**
     * 0关闭，1等待付款，2已付款，等待交易，3完成
     */
    private Integer state;

    @TableId(value = "commerceid", type = IdType.AUTO)
    private Integer commerceid;


}
