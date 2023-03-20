package com.example.ehmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.ehmall.Util.RedissonBloomFilterOfPhone;
import com.example.ehmall.Util.TracingHelper;
import com.example.ehmall.entity.User;
import com.example.ehmall.mapper.UserMapper;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author slh
 * @since 2023-03-13
 */
@RestController
@RequestMapping("/user")
@Api(tags="用户账号接口")
public class UserController
{
    /**
     * 手机号登录的用户，插入至数据库
     * @param phone 手机号
     * @return 是否成功插入
     * @author 施立豪
     * @time 2023/3/18
     */
    @Autowired
    private UserMapper userMapper;
    @ApiOperation(value = "手机号登录添加用户",notes = "添加用户")
    @GetMapping("/insertuserbyphone")
    public boolean InsertUserByPhone(@ApiParam(name="phone",required = true)
                        @RequestParam String phone)
    {
        /**
         * 首先查询该手机号的用户是否存在
         * tip：1.项目采用redis缓存，每一个手机号都会在redis中进行一次缓存，而用户登录时首先查询redis缓存
         * 如果redis查询不存在，则表明用户未注册，此时说明mysql中也没用该记录，对改手机号进行注册；
         * 2.注册流程：1）插入mysql，等待mysql插入成功后，插入redis，两次插入都完成，则用户注册成功
         * 假设1：用户注册时，数据插入mysql成功，但是插入redis失败，用户再次登录，此时系统当作用户未注册，
         * 执行注册流程1），此时会再次插入mysql，产生重复数据，会造成后续处理上的问题。
         * 为了解决该问题，这里的插入接口，选择先进行查询，查询手机号不存在再进行插入，否则直接返回插入成功
         * 这样做会增大一定的开销，但是可以避免一些数据冗余造成的问题。
         * 而mysql中的存放用户数据的user表以id为主键，考虑到对非主键phone的全表查询会很慢，因此对phone列
         * 建立哈希索引，提高查询效率。
         * @author 施立豪
         * @time 2023/3/18
         */
        boolean result1=false;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("手机号添加用户").withTag("controller", "InsertUserByPhone").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            // 业务逻辑写这里
            tracer.activeSpan().setTag("type", "mysql");

            LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
            lqw.eq(User::getPhone, phone);
            User loginUser = userMapper.selectOne(lqw);
            /**
             * 如果不存在则插入
             */
            if(loginUser==null){
                User user = new User();
                user.setPhone(phone);
                user.setState(true);
                int result=userMapper.insert(user);
                result1= result==1;}
            /**
             * 如果存在则直接返回插入成功
             */
            else
            result1=true;
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();
            return result1;
        }
    }

    /**
     * 封掉特点手机号的用户账户
     * @param phone 手机号
     * @return  是否正确封禁
     * @author 施立豪
     * @time 2023/3/18
     */
    @ApiOperation(value = "根据手机号封禁用户",notes = "封禁用户")
    @GetMapping("/banuserbyphone")
    public boolean BanUserByPhone(@ApiParam(name="phone",required = true)
                        @RequestParam String phone)
    {
        /**
         * 查询到被封手机号用户的实体
         * 只更新一个属性，账号状态设置为0-表示被封
         */
        boolean result1=false;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("手机号封禁用户").withTag("controller", "BanUserByPhone").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            // 业务逻辑写这里
            tracer.activeSpan().setTag("type", "mysql");

            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("phone",phone).set("state", 0);
            int result=userMapper.update(null, updateWrapper);
            result1=(result==1);
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();
            return result1;
        }

    }


}

