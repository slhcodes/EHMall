package com.example.ehmall.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommerceInfo {
    private Commerce commerce;
    private Commodity commodity;
    private PartUserInfo user;
}
