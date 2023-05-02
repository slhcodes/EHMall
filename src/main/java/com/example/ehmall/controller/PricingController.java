package com.example.ehmall.controller;


import com.example.ehmall.entity.Comment;
import com.example.ehmall.entity.Pricing;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.mapper.PricingMapper;
import com.example.ehmall.service.impl.CommentServiceImpl;
import com.example.ehmall.service.impl.PricingServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author slh
 * @since 2023-05-02
 */
@Api(tags="出价接口")
@RestController
@RequestMapping("/pricing")
public class PricingController {
    @Autowired
    private PricingServiceImpl pricingService;
    @ApiOperation(value = "获取出价",notes = "成功返回出价实体列表，失败返回null")
    @GetMapping("/get")
    public RespBean getPricing(@ApiParam(name="commodityid",required = true)
                               @RequestParam int commodityid)
    {
        return pricingService.getPricing(commodityid);
    }

    @ApiOperation(value = "插入出价",notes = "成功返回true，失败返回false")
    @PostMapping("/insert")
    public RespBean insertPricing(@RequestBody Pricing
                                              pricing)
    {
        return pricingService.insertPricing(pricing);
    }

}

