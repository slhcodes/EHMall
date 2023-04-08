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
 * @since 2023-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户表的id
     */
    @TableId(value = "user_id", type = IdType.ID_WORKER_STR)
    private Integer userId;

    /**
     * 自我介绍
     */
    private String selfIntroduction;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 兴趣
     */
    private String interest;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 头像url
     */
    private String imageUrl;


}
