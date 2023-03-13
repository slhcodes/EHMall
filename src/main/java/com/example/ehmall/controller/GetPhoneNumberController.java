package com.example.ehmall.controller;

import com.example.ehmall.service.impl.GetPhoneNumberServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2023/3/13 16:41
 * @author 施立豪
 * 用于通过Token获取手机号码的接口
 */
@Api(tags="测试接口")
@RestController
@RequestMapping("/phonenumber")
public class GetPhoneNumberController
{   @GetMapping("/getnumber")
    public String GetNumber(String token) throws Exception {
        GetPhoneNumberServiceImpl getPhoneNumberService=new GetPhoneNumberServiceImpl();
        return getPhoneNumberService.GetPhoneNumber(token);
    }

}
