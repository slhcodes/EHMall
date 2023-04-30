package com.example.ehmall.service;

import com.example.ehmall.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ehmall.entity.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author slh
 * @since 2023-04-30
 */
public interface CommentService extends IService<Comment> {
/**
 * 插入评论
 * @param comment 评论实体
 */
public RespBean insertComment(Comment comment);

    /**
     * 查询商品的评论
     * @param id 商品id
     * @return  评论列表
     */
    public RespBean getComment(int id);
}
