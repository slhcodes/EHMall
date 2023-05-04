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
 * @since 2023-05-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Reward implements Serializable {

    private static final long serialVersionUID = 1L;

    private String description;

    private String url;

    private Integer userid;


    private Date time;

    private Integer state;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String location;

}
