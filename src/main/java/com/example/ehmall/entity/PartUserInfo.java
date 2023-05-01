package com.example.ehmall.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户部分资料类
 * @author 施立豪
 * @time 2023/4/10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PartUserInfo {
    private int id;
    private String imageUrl;
    public PartUserInfo(){}
    public PartUserInfo(int id,String userName , String imageUrl, String signature) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.signature = signature;
        this.userName = userName;
    }

    /**个性签名
     *
     */
    private String signature;
    private String userName;

}
