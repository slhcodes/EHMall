package com.example.ehmall.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.ehmall.entity.Pricing;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.mapper.PricingMapper;
import com.example.ehmall.service.PricingService;
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
 * @since 2023-05-02
 */
@Service
public class PricingServiceImpl extends ServiceImpl<PricingMapper, Pricing> implements PricingService {

    @Autowired
    PricingMapper pricingMapper;
    @Override
    public RespBean insertPricing(Pricing pricing) {
        int id=0;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("插入评论表").withTag("PricingServiceImpl", "insertPricing").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            tracer.activeSpan().setTag("type", "mysql");
            int result=pricingMapper.insert(pricing);
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
    public RespBean getPricing(int id) {
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("查询商品出价表").withTag("PricingServiceImpl", "getPricing").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            tracer.activeSpan().setTag("type", "mysql");
            LambdaQueryWrapper<Pricing> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Pricing::getCommodityid, id);
            List<Pricing> pricingList =pricingMapper.selectList(lqw);
            if (pricingList != null && !pricingList.isEmpty()) {
                return new RespBean(200,"success",pricingList);
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
