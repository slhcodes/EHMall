package com.example.ehmall.controller;
import com.example.ehmall.Util.AssumeRoleUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags="获取密钥接口")
@RequestMapping("/secret")
@RestController
public class OssController {
    @ApiOperation(value = "对象存储", notes = "ID,SECRET,TOKEN")
    @GetMapping("/oos")
    public List<String> GetOOS() throws Exception {
        return AssumeRoleUtil.GetOssCredentials();
    }
}
