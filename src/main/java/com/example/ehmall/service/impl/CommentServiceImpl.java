package com.example.ehmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.ehmall.entity.Comment;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.entity.UserInfo;
import com.example.ehmall.mapper.CommentMapper;
import com.example.ehmall.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ehmall.util.TracingHelper;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author slh
 * @since 2023-04-30
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService
{
    @Autowired
    CommentMapper commentMapper;
    @Override
    public RespBean insertComment(Comment comment) {
        int id=0;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("插入评论表").withTag("CommentServiceImpl", "insertComment").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            tracer.activeSpan().setTag("type", "mysql");
            int result=commentMapper.insert(comment);
            if( result==1)
            {
                return new RespBean(200, "成功", true);
            }
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();
        }
return new RespBean(201, "失败",false);

    }

    @Override
    public RespBean getComment(int id) {
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("查询商品评论表").withTag("CommentServiceImpl", "getComment").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            tracer.activeSpan().setTag("type", "mysql");
        LambdaQueryWrapper<Comment> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Comment::getCommodityid, id);
        List<Comment> commentList = commentMapper.selectList(lqw);
        if (commentList != null && !commentList.isEmpty()) {
            return new RespBean(200,"success",commentList);
        } else {
            System.out.println("No data found.");
        }} catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();
        }
        return new RespBean(201, "失败",null);
    }
}
