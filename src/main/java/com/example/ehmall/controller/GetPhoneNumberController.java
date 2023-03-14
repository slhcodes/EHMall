package com.example.ehmall.controller;

import com.example.ehmall.Util.RsaUtil.RsaServerUtils;
import com.example.ehmall.Util.RsaUtil.RsaServerUtilsImpl;
import com.example.ehmall.service.impl.GetPhoneNumberServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 2023/3/13 16:41
 * @author 施立豪
 * 用于通过Token获取手机号码的接口
 */
@Api(tags="测试接口")
@RestController
@RequestMapping("/phonenumber")
public class GetPhoneNumberController
{   @PostMapping("/getnumber")
    public byte[] GetNumber(String token,String publicKey) throws Exception {
        GetPhoneNumberServiceImpl getPhoneNumberService=new GetPhoneNumberServiceImpl();
    // 服务器要发送的信息
    String info = getPhoneNumberService.GetPhoneNumber(token);
    // 服务器收到公钥后将信息加密
    RsaServerUtils rsaServerUtils = new RsaServerUtilsImpl();
    byte[] cipherText = rsaServerUtils.encrypt(info, publicKey);
    System.out.println(Arrays.toString(cipherText));
        return cipherText;
    }

}
