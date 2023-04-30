package com.example.ehmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.ehmall.entity.Rating;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.entity.User;
import com.example.ehmall.mapper.RatingMapper;
import com.example.ehmall.service.RatingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ehmall.util.TracingHelper;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author slh
 * @since 2023-04-30
 */
@Service
public class RatingServiceImpl extends ServiceImpl<RatingMapper, Rating> implements RatingService {

    @Autowired
    RatingMapper ratingMapper;
    @Override
    public RespBean insertRating(Rating rating) {
        boolean result1=false;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("插入评分数据").withTag("controller", "InsertRating").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            // 业务逻辑写这里
            tracer.activeSpan().setTag("type", "mysql");
            LambdaQueryWrapper<Rating> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Rating::getUserid, rating.getUserid()).eq(Rating::getCommodityid,rating.getCommodityid());
            Rating loginUser = ratingMapper.selectOne(lqw);
            /**
             * 如果不存在则插入
             */
            if(loginUser==null){
                int result=ratingMapper.insert(rating);
                result1=result==1;
            }
            /**
             * 如果存在则更新
             */
            else
            {
                UpdateWrapper<Rating> updateWrapper = new UpdateWrapper<>();

                updateWrapper.eq("userid", rating.getUserid()).eq("commodityid",rating.getCommodityid());
                int result=ratingMapper.update(rating, updateWrapper);
                result1=result==1;
            }
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();

        }
        if(result1){return new RespBean(200,"成功",true);}
    else{
        return new RespBean(201,"失败",false);
    }}
}
