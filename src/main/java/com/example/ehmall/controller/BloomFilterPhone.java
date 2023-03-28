package com.example.ehmall.controller;

import com.example.ehmall.Util.NameUtil;
import com.example.ehmall.Util.RedissonBloomFilterOfPhone;
import com.example.ehmall.Util.RedissonBloomFilterOfQq;
import com.example.ehmall.Util.TracingHelper;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 电话号码查询布隆过滤器实现
 * @author 施立豪
 * @time 2023/3/19
 */
@Api(tags="布隆过滤器电话号码接口")
@RestController
@RequestMapping("/bloomfilterofphone")
public class BloomFilterPhone {
    /**
     *向注册的手机号过滤器加入手机号
     * @param phone 手机号
     * @return 是否添加成功
     */
    @ApiOperation(value = "添加手机号到注册过滤器",notes = "插入")
    @GetMapping("/insertbyphone")
    public boolean InsertUserByPhone(@ApiParam(name="phone",required = true)
                                     @RequestParam String phone)
    {
        boolean result=false;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("添加手机号到注册过滤器").withTag("controller", "InsertUserByPhone").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            // 业务逻辑写这里
            tracer.activeSpan().setTag("type", "redis");
           result= RedissonBloomFilterOfPhone.InsertPhonere(phone);
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();
            return result;
        }

    }
    /**
     *向注册的qq号过滤器加入qq
     * @param qq qq号
     * @return 是否添加成功
     */
    @ApiOperation(value = "添加qq号到注册过滤器",notes = "插入")
    @GetMapping("/insertbyqq")
    public boolean InsertUserByQq(@ApiParam(name="qq",required = true)
                                     @RequestParam String qq)
    {
        boolean result=false;
        Tracer tracer = GlobalTracer.get();
        // 创建spann
        Span span = tracer.buildSpan("添加qq号到注册过滤器").withTag("controller", "InsertUserByQq").start();
        try (Scope ignored = tracer.scopeManager().activate(span,true)) {
            // 业务逻辑写这里
            tracer.activeSpan().setTag("type", "redis");
            result= RedissonBloomFilterOfQq.InsertQqre(qq);
        } catch (Exception e) {
            TracingHelper.onError(e, span);
            throw e;
        } finally {
            span.finish();
            return result;
        }

    }

    /**
     * 查询注册过滤器手机号是否存在
     * @param phone 手机号
     * @return  是否存在
     */
    @ApiOperation(value = "查询手机号是否注册",notes = "查询")
    @GetMapping("/existphone")
    public boolean IsPhoneExist(@ApiParam(name="phone",required = true)
                                     @RequestParam String phone)
    {
        {
            System.out.println(NameUtil.getNickName());
            boolean result=false;
            Tracer tracer = GlobalTracer.get();
            // 创建spann
            Span span = tracer.buildSpan("查询手机号是否注册").withTag("controller", "IsPhoneExist").start();
            try (Scope ignored = tracer.scopeManager().activate(span,true)) {
                tracer.activeSpan().setTag("type", "redis");
                // 业务逻辑写这里
                result=  RedissonBloomFilterOfPhone.IsPhoneExistre(phone);
            } catch (Exception e) {
                TracingHelper.onError(e, span);
                throw e;
            } finally {
                span.finish();
                return result;
            }

        }

    }

    /**
     * 查询注册过滤器QQ号是否存在
     * @param qq 手机号
     * @return  是否存在
     * @time 2023/3/28
     */
    @ApiOperation(value = "查询qq号是否注册",notes = "查询")
    @GetMapping("/existqq")
    public boolean IsQqExist(@ApiParam(name="qq",required = true)
                                @RequestParam String qq)
    {
        {
            System.out.println(NameUtil.getNickName());
            boolean result=false;
            Tracer tracer = GlobalTracer.get();
            // 创建spann
            Span span = tracer.buildSpan("查询qq是否注册").withTag("controller", "IsQQExist").start();
            try (Scope ignored = tracer.scopeManager().activate(span,true)) {
                tracer.activeSpan().setTag("type", "redis");
                // 业务逻辑写这里
                result=  RedissonBloomFilterOfQq.IsQqExistre(qq);
            } catch (Exception e) {
                TracingHelper.onError(e, span);
                throw e;
            } finally {
                span.finish();
                return result;
            }

        }

    }

    /**
     * 查询封号过滤器手机号是否存在
     * @param phone 手机号
     * @return 是否存在
     */
    @ApiOperation(value = "查询手机号是否被封",notes = "查询")
    @GetMapping("/isphonebaned")
    public boolean IsPhoneBaned(@ApiParam(name="phone",required = true)
                                @RequestParam String phone)
    {
        {

            boolean result=false;
            Tracer tracer = GlobalTracer.get();
            // 创建spann
            Span span = tracer.buildSpan("查询手机号是否被封").withTag("controller", "IsPhoneBaned").start();
            try (Scope ignored = tracer.scopeManager().activate(span,true)) {
                tracer.activeSpan().setTag("type", "redis");
                // 业务逻辑写这里
                result=   RedissonBloomFilterOfPhone.IsPhoneBaned(phone);
            } catch (Exception e) {
                TracingHelper.onError(e, span);
                throw e;
            } finally {
                span.finish();
                return result;
            }

        }

    }

    /**
     * 查询封号过滤器qq号是否存在
     * @param qq 手机号
     * @return 是否存在
     */
    @ApiOperation(value = "查询qq号是否被封",notes = "查询")
    @GetMapping("/isqqbaned")
    public boolean IsQqBaned(@ApiParam(name="qq",required = true)
                                @RequestParam String qq)
    {
        {

            boolean result=false;
            Tracer tracer = GlobalTracer.get();
            // 创建spann
            Span span = tracer.buildSpan("查询qq号是否被封").withTag("controller", "IsPhoneBaned").start();
            try (Scope ignored = tracer.scopeManager().activate(span,true)) {
                tracer.activeSpan().setTag("type", "redis");
                // 业务逻辑写这里
                result=   RedissonBloomFilterOfQq.IsQqBaned(qq);
            } catch (Exception e) {
                TracingHelper.onError(e, span);
                throw e;
            } finally {
                span.finish();
                return result;
            }

        }

    }

    /**
     * 插入手机号到封号过滤器
     * @param phone 手机号
     * @return  是否成功
     */
    @ApiOperation(value = "添加手机号到封号过滤器",notes = "插入")
    @GetMapping("/banphone")
    public boolean BanPhone(@ApiParam(name="phone",required = true)
                            @RequestParam String phone)
    {
        {
            boolean result=false;
            Tracer tracer = GlobalTracer.get();
            // 创建spann
            Span span = tracer.buildSpan("查询手机号是否被封").withTag("controller", "BanPhone").start();
            try (Scope ignored = tracer.scopeManager().activate(span,true)) {
                tracer.activeSpan().setTag("type", "redis");
                // 业务逻辑写这里
                result=  RedissonBloomFilterOfPhone.AddBanedPhone(phone);
            } catch (Exception e) {
                TracingHelper.onError(e, span);
                throw e;
            } finally {
                span.finish();
                return result;
            }

        }

    }

    /**
     * 插入qq号到封号过滤器
     * @param qq qq号
     * @return  是否成功
     */
    @ApiOperation(value = "添加qq号到封号过滤器",notes = "插入")
    @GetMapping("/banqq")
    public boolean BanQq(@ApiParam(name="qq",required = true)
                            @RequestParam String qq)
    {
        {
            boolean result=false;
            Tracer tracer = GlobalTracer.get();
            // 创建spann
            Span span = tracer.buildSpan("查询qq号是否被封").withTag("controller", "Banqq").start();
            try (Scope ignored = tracer.scopeManager().activate(span,true)) {
                tracer.activeSpan().setTag("type", "redis");
                // 业务逻辑写这里
                result=  RedissonBloomFilterOfQq.AddBanedQq(qq);
            } catch (Exception e) {
                TracingHelper.onError(e, span);
                throw e;
            } finally {
                span.finish();
                return result;
            }

        }

    }
}
