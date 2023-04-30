package com.example.ehmall.controller;


import com.example.ehmall.entity.Comment;
import com.example.ehmall.entity.Rating;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.service.impl.RatingServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author slh
 * @since 2023-04-30
 */
@RestController
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

