package com.example.ehmall.controller;


import com.example.ehmall.entity.Comment;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.service.CommentService;
import com.example.ehmall.service.impl.CommentServiceImpl;
import com.example.ehmall.service.impl.UserInfoServiceImpl;
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
 * @since 2023-04-30
 */

@Api(tags="评论接口")
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;
    @ApiOperation(value = "获取评论",notes = "成功返回评论实体列表，失败返回null")
    @GetMapping("/get")
    public RespBean getComment(@ApiParam(name="commodityid",required = true)
                             @RequestParam int commodityid)
    {
        return commentService.getComment(commodityid);
    }

    @ApiOperation(value = "插入评论",notes = "成功返回true，失败返回false")
    @PostMapping("/insert")
    public RespBean insertcomment(@RequestBody Comment comment)
    {
        return commentService.insertComment(comment);
    }


}

