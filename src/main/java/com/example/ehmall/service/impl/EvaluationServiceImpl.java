package com.example.ehmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.ehmall.entity.*;
import com.example.ehmall.mapper.CommerceMapper;
import com.example.ehmall.mapper.CommodityMapper;
import com.example.ehmall.mapper.EvaluationMapper;
import com.example.ehmall.service.EvaluationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ehmall.util.TracingHelper;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author slh
 * @since 2023-05-08
 */
@Service
public class EvaluationServiceImpl extends ServiceImpl<EvaluationMapper, Evaluation> implements EvaluationService {

    @Autowired
    EvaluationMapper evaluationMapper;
    @Autowired
    CommerceMapper commerceMapper;
    @Autowired
    CommodityMapper commodityMapper;
    @Override
    public RespBean insertCommodityId(Evaluation evaluation) {
        int id=0;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("插入评价表").withTag("EvaluationServiceImpl", "insertComment").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            tracer.activeSpan().setTag("type", "mysql");
            int result=evaluationMapper.insert(evaluation);
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
    public List<EvalutionInfo> getEvaluation(int userid) {
        List<EvalutionInfo> evalutionInfos=new ArrayList<>();
        List<Commerce>commerceList=new ArrayList<>();
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("查询商品评论表").withTag("CommentServiceImpl", "getComment").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            tracer.activeSpan().setTag("type", "mysql");
            LambdaQueryWrapper<Evaluation> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Evaluation::getUserid, userid);
            List<Evaluation> commentList = evaluationMapper.selectList(lqw);
            if (commentList != null && !commentList.isEmpty()) {
                List<Integer> idList=new ArrayList<>();
                for(Evaluation i:commentList){
                  idList.add(i.getCommerid());
                }
                LambdaQueryWrapper<Commerce> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.in(Commerce::getCommerceid, idList);
                List<Commerce> res=   commerceMapper.selectList(queryWrapper);
                List<Integer> commerceIdList=new ArrayList<>();
                for(Commerce j:res){
                    commerceIdList.add(j.getCommodityid());
                }
                    LambdaQueryWrapper<Commodity> queryWrapper1 = new LambdaQueryWrapper<>();
                    queryWrapper1.in(Commodity::getId, commerceIdList);
                    List<Commodity> res1=  commodityMapper.selectList(queryWrapper1);
                for(int k=0;k<idList.size();++k)
                {
                    EvalutionInfo temp=new EvalutionInfo();
                    if(k<commentList.size())
                    {
                        temp.setEvaluation(commentList.get(k));
                    }
                    if(k<res.size())
                    {
                        temp.setCommerce(res.get(k));
                    }
                    if(k<res1.size())
                    {
                        temp.setCommodity(res1.get(k));
                    }
                    evalutionInfos.add(temp);
                }
                return evalutionInfos;

            } else {
                System.out.println("No data found.");
            }} catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();
        }
        return new ArrayList<>();
    }

    @Override
    public RespBean updateEvaluation(Evaluation evaluation) {
        int id=0;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("插入评价表").withTag("EvaluationServiceImpl", "insertComment").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            tracer.activeSpan().setTag("type", "mysql");
            UpdateWrapper<Evaluation> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("userid",evaluation.getUserid()).eq("commerid", evaluation.getCommerid());
            int result=evaluationMapper.update(evaluation, updateWrapper);
            if(result==1){ return new RespBean(200, "成功",true);}
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();
        }
        return new RespBean(201, "失败",false);

    }
}

