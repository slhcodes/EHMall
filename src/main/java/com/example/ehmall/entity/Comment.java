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
 * @since 2023-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "commodityid", type = IdType.ID_WORKER_STR)
    private Integer commodityid;

    private Integer userid;

    private String content;

    private Date time;


}
