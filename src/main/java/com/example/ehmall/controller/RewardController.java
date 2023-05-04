package com.example.ehmall.controller;


import com.example.ehmall.entity.Commodity;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.entity.Reward;
import com.example.ehmall.service.impl.RewardServiceImpl;
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
 * @since 2023-05-04
 */
@RestController
@Api(tags="悬赏接口")
@RequestMapping("/reward")
public class RewardController {
    @Autowired
    RewardServiceImpl rewardService;
    @ApiOperation(value = "插入悬赏",notes = "成功插入悬赏id 状态码200，失败id 0，状态码201")
    @PostMapping("/insert")
    public RespBean insertReward(@RequestBody Reward reward)
    {
        return rewardService.insertReward(reward);
    }

    @ApiOperation(value = "插入悬赏",notes = "成功悬赏列表 状态码200，失败id 0，状态码201  page为第几页 num为一页几条")
    @GetMapping("/get")
    public List<Reward> getRewards(@ApiParam(name="page",required = true)
                                       @RequestParam int page,@ApiParam(name="num",required = true)
    @RequestParam int num)
    {
        return rewardService.getReward(page,num);
    }

}

