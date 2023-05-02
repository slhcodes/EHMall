package com.example.ehmall.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GetFocusRequest {
    /**
     * 用户id列表
     */
    private int[] users;
    /**
     * 第几页数据，
     */
    private int page;

    // getters and setters
}
