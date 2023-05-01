package com.example.ehmall.controller;

import com.example.ehmall.entity.Commodity;
import com.example.ehmall.entity.PartUserInfo;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.service.impl.SearchServiceImpl;
import com.example.ehmall.util.FuzzSearch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Api(tags="模糊搜索接口")
@RequestMapping("/search")
public class SearchController {
    @Autowired
    SearchServiceImpl searchService;
    @ApiOperation(value = "查询用户",notes = "成功返回用户列表，失败返回空")
    @PostMapping("/user")
    public List<PartUserInfo> userSearch(@ApiParam(name="username",required = true)
                         @RequestParam String username) throws IOException {
        return searchService.searchUser(username);
    }

    @ApiOperation(value = "查询商品",notes = "成功返回商品列表，失败返回空")
    @PostMapping("/commodity")
    public List<Commodity> commoditySearch(@ApiParam(name="commodity",required = true)
                                   @RequestParam String commodity) throws IOException {
        return searchService.searchCommodity(commodity);
    }



}
