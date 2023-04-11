package com.example.ehmall.controller;

import com.aliyuncs.exceptions.ClientException;
import com.example.ehmall.util.Nlp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "商品名",notes = "获取文本内的名词")
    @GetMapping("/categorys")
    public List<String> getWords(@ApiParam(name="text",required = true)
                             @RequestParam String text) throws ClientException {
        System.out.println(text);
        return Nlp.getNlpWords(text);
    }

    @ApiOperation(value = "商品类别",notes = "根据文本推测类别")
    @GetMapping("/class")
    public List<String> getClass(@ApiParam(name="text",required = true)
                                 @RequestParam String text) throws ClientException {
        System.out.println(text);
        return Nlp.getNlpCategory(text);
    }
}
