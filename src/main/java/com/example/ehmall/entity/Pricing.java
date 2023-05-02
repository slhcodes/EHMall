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
public class Pricing implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "commodityid", type = IdType.ID_WORKER_STR)
    private Integer commodityid;

    private Integer userid;

    private Double price;

    private Date time;


}
