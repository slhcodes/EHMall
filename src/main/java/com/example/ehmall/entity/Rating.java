package com.example.ehmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2023-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "userid", type = IdType.ID_WORKER_STR)
    private Integer userid;

    private Integer commodityid;

    private Double score;

    private Integer timestamp;


}
