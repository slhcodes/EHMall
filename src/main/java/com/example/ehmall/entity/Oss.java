package com.example.ehmall.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用于传递oss accesskeyid等信息的实体
 * @author 施立豪
 * @time 2023/4/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Oss {
    private String accessId;
    private String accessSecret;
    private String securityToken;
    public Oss(String id,String secret,String token){
        accessId=id;
        accessSecret=secret;
        securityToken=token;
    }
}
