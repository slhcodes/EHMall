package com.example.ehmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ehmall.entity.Commodity;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.entity.Reward;
import com.example.ehmall.mapper.RewardMapper;
import com.example.ehmall.service.RewardService;
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
 * @since 2023-05-04
 */
@Service
public class RewardServiceImpl extends ServiceImpl<RewardMapper, Reward> implements RewardService {
    @Autowired
    RewardMapper rewardMapper;
    @Override
    public RespBean insertReward(Reward reward) {
        int id=0;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("插入悬赏表").withTag("CommodityServiceImpl", "insertCommodity").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            tracer.activeSpan().setTag("type", "mysql");
            int result=rewardMapper.insert(reward);
            if( result==1)
            {
                id=reward.getId();
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
    public List<Reward> getReward(int page, int num)
    {
        List<Reward> finalList = new ArrayList<>();
        PageHelper.startPage(page, num);
        finalList = rewardMapper.selectList(null);
        PageInfo<Reward> pageS = new PageInfo<Reward>(finalList);
        System.out.println(pageS.getPageNum());
        System.out.println(pageS.getPages());
        return pageS.getList();
    }

    @Override
    public List<Reward> getMyReward(int userid) {
        List<Reward> finalList = new ArrayList<>();
        LambdaQueryWrapper<Reward> lqw=new LambdaQueryWrapper<>();
        lqw.eq(Reward::getUserid,userid);
        finalList=rewardMapper.selectList(lqw);
        return finalList;
    }
}
