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
    private String interest;
    public PartUserInfo(){}

    public PartUserInfo(int id, String imageUrl, String selfIntroduction, Integer age, Integer gender, String signature, String userName,String interest) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.selfIntroduction = selfIntroduction;
        this.age = age;
        this.gender = gender;
        this.signature = signature;
        this.userName = userName;
        this.interest=interest;
    }

    /**个性签名
     *
     */
    private String signature;
    private String userName;

}
