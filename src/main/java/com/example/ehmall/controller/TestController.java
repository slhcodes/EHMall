package com.example.ehmall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags="测试接口")
@RestController
public class TestController {
    @ApiOperation(value = "登陆",notes = "获取Hello")
@GetMapping("/test")
    public String test()
{
    System.out.println("hhh");//
    return "Hi,human!";
}
}
