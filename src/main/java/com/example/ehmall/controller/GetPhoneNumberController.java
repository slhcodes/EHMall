package com.example.ehmall.controller;

import com.example.ehmall.util.RsaUtil.RsaServerUtils;
import com.example.ehmall.util.RsaUtil.RsaServerUtilsImpl;
import com.example.ehmall.util.TracingHelper;
import com.example.ehmall.service.impl.GetPhoneNumberServiceImpl;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 2023/3/13 16:41
 * @author 施立豪
 * 用于通过Token获取手机号码的接口
 */
@Api(tags="测试接口")
@RestController
@RequestMapping("/phonenumber")
public class GetPhoneNumberController
{   @PostMapping("/getnumber")
    public byte[] getNumber(String token, String publicKey) throws Exception {
    byte[] cipherText;
    Tracer tracer = GlobalTracer.get();
    // 创建spann
    Span span = tracer.buildSpan("Token获取phoneNumber").withTag("controller", "GetNumber").start();
    try (Scope ignored = tracer.scopeManager().activate(span,true)) {
        // 业务逻辑写这里
        tracer.activeSpan().setTag("type", "http");

        GetPhoneNumberServiceImpl getPhoneNumberService=new GetPhoneNumberServiceImpl();
        // 服务器要发送的信息
        String info = getPhoneNumberService.getPhoneNumber(token);
        // 服务器收到公钥后将信息加密
        RsaServerUtils rsaServerUtils = new RsaServerUtilsImpl();
        cipherText = rsaServerUtils.encrypt(info, publicKey);
        System.out.println(Arrays.toString(cipherText));
    } catch (Exception e) {
        TracingHelper.onError(e, span);
        throw e;
    } finally {
        span.finish();
    }
        return cipherText;
    }

}
