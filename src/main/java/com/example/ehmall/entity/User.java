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
 * 用户登录信息实体
 *
 * @author slh
 * @since 2023-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String phone;

    private String wechat;

    private String qq;
    /**
     * 用户状态，是否 未被封禁，
     */
    private Boolean state;

    private String username;


}
