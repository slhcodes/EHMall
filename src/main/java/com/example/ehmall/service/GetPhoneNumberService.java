package com.example.ehmall.service;
/**
 * <p>
 *  电话号码获取服务类
 * </p>
 *
 * @author 施立豪
 * @since 2023-03-13 16：51
 */
public interface GetPhoneNumberService {
    public  com.aliyun.dypnsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) ;
}
