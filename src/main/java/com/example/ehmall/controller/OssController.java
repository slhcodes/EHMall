package com.example.ehmall.controller;
import com.example.ehmall.util.AssumeRoleUtil;
import com.example.ehmall.entity.Oss;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 对象存储接口
 * @author 施立豪
 */
@Api(tags="获取密钥接口")
@RequestMapping("/secret")
@RestController
public class OssController {
    @ApiOperation(value = "对象存储", notes = "ID,SECRET,TOKEN")
    @GetMapping("/oos")
    public Oss getOOS() throws Exception {
        return AssumeRoleUtil.getOssCredentials();
    }
}
