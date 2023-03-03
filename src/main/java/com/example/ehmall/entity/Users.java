package com.example.ehmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author slh
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "测试",description = "测试用户")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "ID")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String userPassword;
    @ApiModelProperty(value = "电话")
    private String userTelephone;
    @ApiModelProperty(value = "性别")
    private String userGender;


}
