package com.example.ehmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.ehmall.Util.TracingHelper;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.entity.User;
import com.example.ehmall.entity.UserInfo;
import com.example.ehmall.mapper.UserInfoMapper;
import com.example.ehmall.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2023-04-08
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    /**
     * 修改用户头像
     * @return true 修改成功 false 修改失败
     */
    @Autowired
    private UserInfoMapper userInfoMapper;
    public RespBean setImage(int userId, String imageUrl)
    {
        boolean result1=false;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("用户更新头像").withTag("controller", "setImage").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            // 业务逻辑写这里
            tracer.activeSpan().setTag("type", "mysql");
            UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_id",userId).set("image_url", imageUrl);
            int result=userInfoMapper.update(null, updateWrapper);
            result1=(result==1);
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();
            if(result1==true)
                return new RespBean(200,"成功",result1);
            else
                return new RespBean(200,"失败",result1);
        }
    };
    /**
     * 获取用户头像
     * @return url地址
     * @author slh
     * @time 2023/4/8
     */
    public RespBean getImage(int userId) {

        /**
         * 查询到用户资料的实体
         * 获取其URL返回
         */
        String result1 = "";
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("用户id查询图片地址").withTag("controller", "getImage").start();
        try (Scope ignored = tracer.scopeManager().activate(span, true)) {
            tracer.activeSpan().setTag("type", "mysql");
            LambdaQueryWrapper<UserInfo> lqw = new LambdaQueryWrapper<UserInfo>();
            /**
             * 查询到userid的实体
             */
            lqw.eq(UserInfo::getUserId, userId);
            /**
             * 获取url
             */
            UserInfo curUser = userInfoMapper.selectOne(lqw);
            result1 = curUser.getImageUrl();
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();
            if (result1.length() > 0)
                return new RespBean(200, "成功", result1);
            else return new RespBean(200, "url为空", result1);
        }
    }

    /**
     * 插入用户接口
     * @param id 用户id
     * @param userName 用户名
     * @return true 成功，false 失败
     */
    public RespBean InsertUser(int id,String userName)
    {
        boolean result1=false;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("插入用户到用户资料表").withTag("UserInfoServiceImpl", "InsertUser").start();
        try (Scope ignored = tracer.scopeManager().activate(span, true)) {
            tracer.activeSpan().setTag("type", "mysql");
            UserInfo user = new UserInfo();
            user.setUserId(id);
            user.setUsername(userName);
            int result=userInfoMapper.insert(user);
            result1= result==1;
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();
            if (result1)
                return new RespBean(200, "成功", result1);
            else return new RespBean(200, "失败", result1);
        }
    }
}
