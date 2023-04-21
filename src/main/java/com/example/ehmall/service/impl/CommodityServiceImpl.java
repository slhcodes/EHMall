package com.example.ehmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.ehmall.entity.Commodity;
import com.example.ehmall.entity.PartUserInfo;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.entity.UserInfo;
import com.example.ehmall.mapper.CommodityMapper;
import com.example.ehmall.service.CommodityService;
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
 * @since 2023-04-21
 */
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService {
    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public Commodity getCommodity(int id) {
        /**
         * 查询到用户资料的实体
         * 获取其URL返回
         */
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("商品id查询商品信息资料").withTag("CommodityServiceImpl", " getCommodity").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            tracer.activeSpan().setTag("type", "mysql");
            LambdaQueryWrapper<Commodity> lqw = new LambdaQueryWrapper<Commodity>();
            /**
             * 查询到id的实体
             */
            lqw.eq(Commodity::getId, id);
           Commodity curCom = commodityMapper.selectOne(lqw);
            if(curCom!=null)
            {
                return curCom;
            }
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();
        }
        return null;

    }
    @Override
    public RespBean insertCommodity(Commodity commodity) {
        int id=0;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("插入商品表").withTag("CommodityServiceImpl", "insertCommodity").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            tracer.activeSpan().setTag("type", "mysql");
            int result=commodityMapper.insert(commodity);
           if( result==1)
           {
               id=commodity.getId();
           }
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();

        }   if (id!=0){
            return new RespBean(200, "成功", id);}
        else {return new RespBean(201, "失败",0);}
    }
}
