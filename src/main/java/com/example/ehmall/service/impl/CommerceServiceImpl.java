package com.example.ehmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.ehmall.entity.*;
import com.example.ehmall.mapper.CommerceMapper;
import com.example.ehmall.mapper.CommodityMapper;
import com.example.ehmall.mapper.UserInfoMapper;
import com.example.ehmall.service.CommerceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ehmall.util.TracingHelper;
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
 * @since 2023-05-02
 */
@Service
public class CommerceServiceImpl extends ServiceImpl<CommerceMapper, Commerce> implements CommerceService {
    @Autowired
    CommerceMapper commerceMapper;
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
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
            updateWrapper.eq("commerceid", commerceId).setSql(" state = state +1");
           int rows= commerceMapper.update(null,updateWrapper);
            // 执行更新操作，并获取更新后的数据
            if(rows==1){
            Commerce commerce = commerceMapper.selectOne(updateWrapper);
            if(commerce!=null)
            {
                System.out.println("commerce.getState()"+commerce.getState());
                return new RespBean(200,"成功",commerce.getState());
            }}

        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();
        }

            return new RespBean(201,"失败",0);

    }
    public Commerce getCommerce(int commodityid,int sellerid,int buyerid)
    {
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("更新交易状态").withTag("CommerceServiceImpl", "updateState").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            // 业务逻辑写这里
            tracer.activeSpan().setTag("type", "mysql");
            LambdaQueryWrapper<Commerce> lqw = new LambdaQueryWrapper<Commerce>();
            /**
             * 查询到id的实体
             */
            lqw.eq(Commerce::getCommodityid, commodityid)
                    .eq(Commerce::getSellerid,sellerid)
                    .eq(Commerce::getBuyerid,buyerid);
            Commerce curCom = commerceMapper.selectOne(lqw);
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
    public List<CommerceInfo> getMyCommerce(int userid) {
        List<Commerce> res=new ArrayList<>();
        List<CommerceInfo> result=new ArrayList<>();
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("更新交易状态").withTag("CommerceServiceImpl", "updateState").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            // 业务逻辑写这里
            tracer.activeSpan().setTag("type", "mysql");
            LambdaQueryWrapper<Commerce> lqw = new LambdaQueryWrapper<Commerce>();
            /**
             * 查询到id的实体
             */
            lqw.eq(Commerce::getState, 3)
                    .and(wrapper -> wrapper.eq(Commerce::getSellerid, userid).or().eq(Commerce::getBuyerid, userid));
            res= commerceMapper.selectList(lqw);
            if(!res.isEmpty())
            {
                List<Integer> idList=new ArrayList<>();
                List<Integer> userIdList=new ArrayList<>();
                for(Commerce i:res){
                    idList.add(i.getCommodityid());
                    if(i.getBuyerid()==userid)
                    {
                        userIdList.add(i.getSellerid());
                    }else
                    {
                        userIdList.add(i.getBuyerid());
                    }
                }
                LambdaQueryWrapper<Commodity> queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.in(Commodity::getId, idList);
                List<Commodity> res1=  commodityMapper.selectList(queryWrapper1);
                LambdaQueryWrapper<UserInfo> queryWrapper2 = new LambdaQueryWrapper<>();
                queryWrapper2.in(UserInfo::getUserId, userIdList);
                List<UserInfo> res2=  userInfoMapper.selectList(queryWrapper2);
                for(int i=0;i<res.size();++i)
                {
                    CommerceInfo temp=new CommerceInfo();
                    temp.setCommerce(res.get(i));
                    for(Commodity j:res1){
                        if(j.getId().equals(res.get(i).getCommodityid()))
                        {
                    temp.setCommodity(j);break;}}
                    if(res.get(i).getBuyerid()==userid){
                    for(UserInfo u:res2){
                        if(u.getUserId().equals(res.get(i).getSellerid())){
                            temp.setUser(new PartUserInfo(u.getUserId(),u.getImageUrl(),u.getSelfIntroduction(),u.getAge(),u.getGender(),u.getSignature(),u.getUsername(),u.getInterest()));break;}}}
                    else
                    {
                        for(UserInfo u:res2){
                            if(u.getUserId().equals(res.get(i).getBuyerid())){
                                temp.setUser(new PartUserInfo(u.getUserId(),u.getImageUrl(),u.getSelfIntroduction(),u.getAge(),u.getGender(),u.getSignature(),u.getUsername(),u.getInterest()));break;}}
                    }
                          result.add(temp);
                }

            }
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();
        }
        return result;
    }
}
