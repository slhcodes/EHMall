package com.example.ehmall.controller;

import com.example.ehmall.Util.RedissonBloomFilterOfPhone;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="布隆过滤器电话号码接口")
@RestController
@RequestMapping("/bloomfilterofphone")
public class BloomFilterPhone {

    @ApiOperation(value = "添加手机号到注册过滤器",notes = "插入")
    @GetMapping("/insertbyphone")
    public boolean InsertUserByPhone(@ApiParam(name="phone",required = true)
                                     @RequestParam String phone)
    {
        return RedissonBloomFilterOfPhone.InsertPhone(phone);
    }
    @ApiOperation(value = "查询手机号是否注册",notes = "查询")
    @GetMapping("/existphone")
    public boolean IsPhoneExist(@ApiParam(name="phone",required = true)
                                     @RequestParam String phone)
    {
        return RedissonBloomFilterOfPhone.IsPhoneExist(phone);
    }
    @ApiOperation(value = "查询手机号是否被封",notes = "查询")
    @GetMapping("/isphonebaned")
    public boolean IsPhoneBaned(@ApiParam(name="phone",required = true)
                                @RequestParam String phone)
    {
        return RedissonBloomFilterOfPhone.IsPhoneBaned(phone);
    }
    @ApiOperation(value = "添加手机号到封号过滤器",notes = "插入")
    @GetMapping("/banphone")
    public boolean BanPhone(@ApiParam(name="phone",required = true)
                            @RequestParam String phone)
    {
        return RedissonBloomFilterOfPhone.AddBanedPhone(phone);
    }
}
