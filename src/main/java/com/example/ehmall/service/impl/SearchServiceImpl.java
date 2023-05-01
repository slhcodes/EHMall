package com.example.ehmall.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.ehmall.entity.Commodity;
import com.example.ehmall.entity.PartUserInfo;
import com.example.ehmall.entity.UserInfo;
import com.example.ehmall.mapper.CommodityMapper;
import com.example.ehmall.mapper.UserInfoMapper;
import com.example.ehmall.service.SearchService;
import com.example.ehmall.util.FuzzSearch;
import com.example.ehmall.util.TracingHelper;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private CommodityMapper commodityMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public List<PartUserInfo> searchUser(String userName) throws IOException {
        List<Integer> idList= FuzzSearch.getUser(userName);
        Tracer tracer = GlobalTracer.get();
        List<PartUserInfo> userInfoList=new ArrayList<>();
        // 创建spann
        Span span = tracer.buildSpan("模糊搜索用户").withTag("SearchServiceImpl", " searchUser").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            tracer.activeSpan().setTag("type", "es+redis+mysql");
        for(int i:idList)
        {
            PartUserInfo tempUser =null;
            BoundHashOperations<String, Object, Object> boundHashOperations=redisTemplate.boundHashOps("UserInfo");
            Object aa=boundHashOperations.get(String.valueOf(i));
            if(aa!=null)
            {
                String user=aa.toString();
                tempUser = (PartUserInfo) JSON.parseObject(user,PartUserInfo.class);
                tempUser.setId(i);
                userInfoList.add(tempUser);
            }
            else
            { LambdaQueryWrapper<UserInfo> lqw = new LambdaQueryWrapper<UserInfo>();
                /**
                 * 查询到userid的实体
                 */
                lqw.eq(UserInfo::getUserId, i);
                /**
                 * 获取url
                 */
                UserInfo curUser = userInfoMapper.selectOne(lqw);
                if(curUser!=null)
                {
                   tempUser=new PartUserInfo(curUser.getUserId(),curUser.getUsername(),curUser.getImageUrl(),curUser.getSignature());
                    userInfoList.add(tempUser);
                    String userString=JSON.toJSONString(tempUser);
                    boundHashOperations.put(String.valueOf(i),userString);
                }
            }
        }
    } catch (Exception e) {
        TracingHelper.onError(e, span);
        throw e;
    } finally {
        span.finish();
    }
        return userInfoList;
    }

    @Override
    public List<Commodity> searchCommodity(String comName) throws IOException {
        Tracer tracer = GlobalTracer.get();
        List<Commodity>commodityList=new ArrayList<>();
        List<Integer> idList= FuzzSearch.getCommodity1(comName);
        // 创建spann
        Span span = tracer.buildSpan("模糊搜索商品").withTag("SearchServiceImpl", " searchCommodity").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            tracer.activeSpan().setTag("type", "es+redis+mysql");
        for(int i:idList)
        {
            /**
             * 先查询redis，redis没有查询mysql，逐点查询，如果列表查询mysql效率会快些
             */
            Commodity tempCom =null;
            BoundHashOperations<String, Object, Object> boundHashOperations=redisTemplate.boundHashOps("Commodity");
            Object aa=boundHashOperations.get(String.valueOf(i));

            if(aa!=null)
            {
                String user=aa.toString();
                tempCom = (Commodity) JSON.parseObject(user,Commodity.class);
                commodityList.add(tempCom);
            }
            /**
             * 查询mysql
             */
            else
            {
                LambdaQueryWrapper<Commodity> lqw = new LambdaQueryWrapper<Commodity>();
                /**
                 * 查询到id的实体
                 */
                lqw.eq(Commodity::getId, i);
                tempCom = commodityMapper.selectOne(lqw);
                if(tempCom!=null)
                {
                    commodityList.add(tempCom);
                    String userString=JSON.toJSONString(tempCom);
                    boundHashOperations.put(String.valueOf(i),userString);
                }
            }

        }
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();
        }
        return commodityList;
    }


}
