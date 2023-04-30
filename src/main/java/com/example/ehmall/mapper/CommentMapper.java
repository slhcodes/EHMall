package com.example.ehmall.mapper;

import com.example.ehmall.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.ManagedBean;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author slh
 * @since 2023-04-30
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
