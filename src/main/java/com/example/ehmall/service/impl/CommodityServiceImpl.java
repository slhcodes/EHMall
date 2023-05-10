package com.example.ehmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.ehmall.entity.*;
import com.example.ehmall.mapper.CommodityMapper;
import com.example.ehmall.service.CommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ehmall.util.TracingHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Commodity> getFocusedCommodity(int[] users, int pageIndex) {
            List<Commodity> finalList = new ArrayList<>();
            PageHelper.startPage(pageIndex, 10);
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();

            for(int i:users)
            {
                queryWrapper.eq("user_id", i).or();
            }
             finalList = commodityMapper.selectList(queryWrapper);

            PageInfo<Commodity> page = new PageInfo<Commodity>(finalList);

            System.out.println(page.getPageNum());
            System.out.println(page.getPages());
            return page.getList();
    }

    @Override
    public List<Commodity> getFocusedCommodity(int userid) {
        List<Commodity> finalList = new ArrayList<>();
        LambdaQueryWrapper<Commodity> lqw=new LambdaQueryWrapper<>();
        lqw.eq(Commodity::getUserId,userid);
        finalList=commodityMapper.selectList(lqw);
        return finalList;
    }

}
