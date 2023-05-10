package com.example.ehmall.controller;


import com.example.ehmall.entity.Commodity;
import com.example.ehmall.entity.GetFocusRequest;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.entity.Reward;
import com.example.ehmall.mapper.UserInfoMapper;
import com.example.ehmall.service.CommodityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author slh
 * @since 2023-04-21
 */
@RestController
@Api(tags="商品接口")
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;
    @ApiOperation(value = "插入商品",notes = "成功插入商品id 状态码200，失败id 0，状态码201")
    @PostMapping("/insert")
public RespBean insertCommodity(@RequestBody Commodity commodity)
    {
    return commodityService.insertCommodity(commodity);
    }
    @GetMapping("/get")
    @ApiOperation(value = "获取商品",notes = "返回实体，失败null")

    public Commodity getCommodity(@ApiParam(name="id",required = true)
    @RequestParam int id)
    {
        return commodityService.getCommodity(id);
    }
    @PostMapping("/getfocus")
    @ApiOperation(value = "获取关注用户发布的商品",notes = "返回商品列表，失败null")

    public List<Commodity> getFocusCommodities(@RequestBody GetFocusRequest getFocusRequest)
    {
        return commodityService.getFocusedCommodity(getFocusRequest.getUsers(), getFocusRequest.getPage());
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新商品",notes = "成功code=200，失败201")

    public RespBean updateCommodity(@RequestBody Commodity commodity)
    {
        return commodityService.updateCommodity(commodity);
    }

    @ApiOperation(value = "获取我的发布",notes = "成功：发布列表 状态码200，失败id 0，状态码201  page为第几页 num为一页几条")
    @GetMapping("/getmy")
    public List<Commodity> getMyRewards(@ApiParam(name="userid",required = true)
                                     @RequestParam int userid)
    {
        return commodityService.getFocusedCommodity(userid);
    }
}

