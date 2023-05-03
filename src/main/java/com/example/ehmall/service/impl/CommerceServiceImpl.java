package com.example.ehmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.ehmall.entity.Commerce;
import com.example.ehmall.entity.Rating;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.mapper.CommerceMapper;
import com.example.ehmall.service.CommerceService;
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
 * @since 2023-05-02
 */
@Service
public class CommerceServiceImpl extends ServiceImpl<CommerceMapper, Commerce> implements CommerceService {
    @Autowired
    CommerceMapper commerceMapper;
    @Override
    public RespBean insertCommerce(Commerce commerce) {
        int id=0;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("插入交易").withTag(" CommerceServiceImpl", "insertCommerce").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            tracer.activeSpan().setTag("type", "mysql");
            int result=commerceMapper.insert(commerce);
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
    public RespBean updateState(int commerceId, int state) {
        boolean result1=false;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("更新交易状态").withTag("CommerceServiceImpl", "updateState").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            // 业务逻辑写这里
            tracer.activeSpan().setTag("type", "mysql");

                UpdateWrapper<Commerce> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("commerceid",commerceId).set("state",state);
                int result=commerceMapper.update(null, updateWrapper);
                result1=result==1;
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();
        }
        if(result1){return new RespBean(200,"成功",true);}
        else{
            return new RespBean(201,"失败",false);
        }
    }
}
