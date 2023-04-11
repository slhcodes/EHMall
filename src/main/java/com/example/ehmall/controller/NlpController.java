package com.example.ehmall.controller;

import com.aliyuncs.exceptions.ClientException;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.service.impl.UserInfoServiceImpl;
import com.example.ehmall.util.Nlp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分词接口
 * @author 施立豪
 * @time 2023/4/11
 */
@RestController
@RequestMapping("/nlp")
@Api(tags="Nlp分类接口")
public class NlpController {
    @ApiOperation(value = "分类",notes = "获取文本内的名词")
    @GetMapping("/categorys")
    public List<String> setImage(@ApiParam(name="text",required = true)
                             @RequestParam String text) throws ClientException {
        System.out.println(text);
        return Nlp.getNlpWords(text);
    }
}
