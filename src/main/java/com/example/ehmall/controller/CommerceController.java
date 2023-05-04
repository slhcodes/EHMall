package com.example.ehmall.controller;


import com.example.ehmall.entity.Commerce;
import com.example.ehmall.entity.Pricing;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.service.impl.CommerceServiceImpl;
import com.example.ehmall.service.impl.PricingServiceImpl;
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
@RestController
@ApiOperation("交易接口")
@RequestMapping("/commerce")
public class CommerceController {
    @Autowired
    private CommerceServiceImpl commerceService;
    @ApiOperation(value = "更新状态",notes = "成功返回true，失败返回false;state:0关闭，1等待付款，2已付款，等待交易，3完成")
    @GetMapping("/update")
    public RespBean getPricing(@ApiParam(name="commerceid",required = true)
                               @RequestParam int commerceid,@ApiParam(name="state",required = true)
    @RequestParam int state)
    {
        return commerceService.updateState(commerceid,state);
    }

    @ApiOperation(value = "获取交易",notes = "成功返回交易实体，失败返回false;state:0关闭，1等待付款，2已付款，等待交易，3完成")
    @GetMapping("/get")
    public Commerce getCommerce(@ApiParam(name="commodityid",required = true)
                               @RequestParam int commodityid,@ApiParam(name="sellerid",required = true)
                               @RequestParam int sellerid,@ApiParam(name="buyerid",required = true)
    @RequestParam int buyerid)
    {
        return commerceService.getCommerce(commodityid,sellerid,buyerid);
    }

    @ApiOperation(value = "插入交易",notes = "成功返回true，失败返回false")
    @PostMapping("/insert")
    public RespBean insertPricing(@RequestBody Commerce
                                          commerce)
    {
        return commerceService.insertCommerce(commerce);
    }

}

