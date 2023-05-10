package com.example.ehmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.ehmall.util.SpringContextHolder;
import com.example.ehmall.util.TracingHelper;
import com.example.ehmall.entity.PartUserInfo;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.entity.UserInfo;
import com.example.ehmall.mapper.UserInfoMapper;
import com.example.ehmall.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;


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
    @Autowired
    private RedisTemplate <String, String> redisTemplate;

    public RespBean setImage(int userId, String imageUrl)
    {
        boolean result1=false;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("用户更新头像").withTag("controller", "setImage").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            // 查询用户id的记录，修改记录的image_url
            tracer.activeSpan().setTag("type", "mysql");
            UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ");
            Date date = new Date(System.currentTimeMillis());
            System.out.println(formatter.format(date));
            updateWrapper.eq("user_id",userId).set("image_url", imageUrl).set("updated_at",formatter.format(date));
            int result=userInfoMapper.update(null, updateWrapper);
            result1=(result==1);
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();

        }    if(result1){
        deletePartUserInfo(userId);
        return new RespBean(200,"成功",result1);}
    else
    { return new RespBean(200,"失败",result1);}
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
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
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
            /**
             * 用户不在表中，需要插入用户并返回空
             */
            if(curUser==null)
            {
                insertUser(userId,"用户"+userId);
                result1="";
            }
            else {
            result1 = curUser.getImageUrl();
                }
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();
        }
        if (result1==null)
        { return new RespBean(200, "url为空", result1);}
        else {return new RespBean(200, "成功", result1);}
    }
    /**
     * 修改用户名
     * @param id        id
     * @param username 新的用户名
     * @return   是否成功
     */
    @Override
    public RespBean setUsername(int id, String username) {
        boolean result1=false;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("用户修改用户名").withTag("controller", "setUsername").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            // 查询用户id的记录，修改记录的image_url
            tracer.activeSpan().setTag("type", "mysql");
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
            Date date = new Date(System.currentTimeMillis());
            System.out.println(formatter.format(date));
            UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_id",id).set("username", username).set("updated_at",formatter.format(date));
            int result=userInfoMapper.update(null, updateWrapper);
            result1=(result==1);
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();

        }    if(result1){
            deletePartUserInfo(id);
            return new RespBean(200,"成功",result1);}
        else
        { return new RespBean(200,"失败",result1);}
    }

    /**
     * 插入用户接口
     * @param id 用户id
     * @param userName 用户名
     * @return true 成功，false 失败
     */
    public RespBean insertUser(int id, String userName)
    {
        boolean result1=false;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("插入用户到用户资料表").withTag("UserInfoServiceImpl", "InsertUser").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            tracer.activeSpan().setTag("type", "mysql");
            UserInfo user = new UserInfo();
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
            Date date = new Date(System.currentTimeMillis());

            user.setUpdated_at(date);
            user.setUserId(id);
            user.setUsername(userName);
            user.setImageUrl("IMG_4814.JPG");
            int result=userInfoMapper.insert(user);
            result1= result==1;
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();

        }   if (result1){
        return new RespBean(200, "成功", result1);}
    else {return new RespBean(200, "失败", result1);}
    }

    /**
     * 获取用户部分资料接口
     * @param userId 用户id
     * @return  资料实体
     * @time 2023/4/10
     */
    @Override
    public PartUserInfo getPartUserInfo(int userId) {
        /**
         * 查询到用户资料的实体
         * 获取其URL返回
         */
        PartUserInfo user=null;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("用户id查询用户部分资料").withTag("controller", "getPartUserInfo").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
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
            if(curUser!=null)
            {
                user=new PartUserInfo(curUser.getUserId(),curUser.getImageUrl(),curUser.getSelfIntroduction(),curUser.getAge(),curUser.getGender(),curUser.getSignature(),curUser.getUsername(),curUser.getInterest());
            }
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();
        }
        return user;
    }
    /**
     * 修改性别
     * @param id
     * @param gender
     * @time 2023/4/25
     * @return
     */
    public RespBean setGender(int id,Boolean gender){ boolean result1=false;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("用户修改用户名").withTag("controller", "setUsername").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            // 查询用户id的记录，修改记录的image_url
            tracer.activeSpan().setTag("type", "mysql");
            UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
            Date date = new Date(System.currentTimeMillis());
            System.out.println(formatter.format(date));
            updateWrapper.eq("user_id",id).set("gender", gender) .set("updated_at",formatter.format(date));
            int result=userInfoMapper.update(null, updateWrapper);
            result1=(result==1);
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();

        }    if(result1){
            deletePartUserInfo(id);
            return new RespBean(200,"成功",result1);}
        else
        { return new RespBean(200,"失败",result1);}}

    @Override
    public RespBean setAge(int id, int age) {
        boolean result1=false;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("用户修改用户名").withTag("controller", "setUsername").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            // 查询用户id的记录，修改记录的image_url
            tracer.activeSpan().setTag("type", "mysql");
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
            Date date = new Date(System.currentTimeMillis());
            System.out.println(formatter.format(date));
            UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_id",id).set("age", age).set("updated_at",formatter.format(date));
            int result=userInfoMapper.update(null, updateWrapper);
            result1=(result==1);
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();

        }    if(result1){
            deletePartUserInfo(id);
            return new RespBean(200,"成功",result1);}
        else
        { return new RespBean(200,"失败",result1);}
    }

    @Override
    public RespBean setInterest(int id, String interest) {
        boolean result1=false;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("用户修改用户名").withTag("controller", "setUsername").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            // 查询用户id的记录，修改记录的image_url
            tracer.activeSpan().setTag("type", "mysql");
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
            Date date = new Date(System.currentTimeMillis());
            System.out.println(formatter.format(date));
            UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_id",id).set("interest", interest).set("updated_at",formatter.format(date));
            int result=userInfoMapper.update(null, updateWrapper);
            result1=(result==1);
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();

        }    if(result1){
            deletePartUserInfo(id);
            return new RespBean(200,"成功",result1);}
        else
        { return new RespBean(200,"失败",result1);}
    }

    @Override
    public RespBean setIntro(int id, String introduction) {
        boolean result1=false;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("用户修改用户名").withTag("controller", "setUsername").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            // 查询用户id的记录，修改记录的image_url
            tracer.activeSpan().setTag("type", "mysql");
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
            Date date = new Date(System.currentTimeMillis());
            System.out.println(formatter.format(date));
            UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_id",id).set("self_introduction", introduction).set("updated_at",formatter.format(date));
            int result=userInfoMapper.update(null, updateWrapper);
            result1=(result==1);
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();

        }    if(result1){
            deletePartUserInfo(id);
            return new RespBean(200,"成功",result1);}
        else
        { return new RespBean(200,"失败",result1);}
    }

    /**
     * 修改个签
     * @param id
     * @param signature
     * @time 2023/4/25
     * @return
     */
    public RespBean setSignature(int id,String signature){ boolean result1=false;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("用户修改用户名").withTag("controller", "setUsername").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            // 查询用户id的记录，修改记录的image_url
            tracer.activeSpan().setTag("type", "mysql");
            UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
            Date date = new Date(System.currentTimeMillis());
            System.out.println(formatter.format(date));
            updateWrapper.eq("user_id",id).set("signature", signature)  .set("updated_at",formatter.format(date));;
            int result=userInfoMapper.update(null, updateWrapper);
            result1=(result==1);
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();
        }    if(result1)
        {
            deletePartUserInfo(id);
            return new RespBean(200,"成功",result1);
        }
        else
        { return new RespBean(200,"失败",result1);}}

    /**
     * 删除redis中用户信息
     * @param userId 用户id
     * @return
     */
    @Override
    public String deletePartUserInfo(int userId)
    {
        BoundHashOperations boundHashOperations=redisTemplate.boundHashOps("UserInfo");
        boundHashOperations.delete(String.valueOf(userId));
        return "weq";
    }
}
