package com.example.ehmall.controller;


import com.example.ehmall.entity.Comment;
import com.example.ehmall.entity.Rating;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.service.impl.RatingServiceImpl;
import com.example.ehmall.util.FuzzSearch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author slh
 * @since 2023-04-30
 */
@RestController
@Api(tags="商品推荐打分接口")

@RequestMapping("/rating")
public class RatingController {
@Autowired
    RatingServiceImpl ratingService;
    @ApiOperation(value = "插入评分",notes = "成功返回true，失败返回false")
    @PostMapping("/insert")
    public RespBean insertRating(@RequestBody Rating rating)
    {
        return ratingService.insertRating(rating);
    }


}

