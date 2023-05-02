package com.example.ehmall;
import com.example.ehmall.controller.*;
import com.example.ehmall.entity.Comment;
import com.example.ehmall.entity.Commerce;
import com.example.ehmall.entity.GetFocusRequest;
import com.example.ehmall.service.CommentService;
import io.opentracing.util.GlobalTracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.bind.annotation.GetMapping;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.example.ehmall.controller.UserInfoController;
import io.opentracing.util.GlobalTracer;
import org.testng.annotations.BeforeClass;

import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
@RunWith(SpringRunner.class)
@SpringBootTest
public class BloomFilterTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private BloomFilterPhone bloomFilterPhone;
    @BeforeClass
    public void ready()
    {
        io.jaegertracing.Configuration config = new io.jaegertracing.Configuration("CUG贰货服务端");
        io.jaegertracing.Configuration.SenderConfiguration sender = new io.jaegertracing.Configuration.SenderConfiguration();
        // 将 <endpoint> 替换为控制台概览页面上相应客户端和地域的接入点。
        sender.withEndpoint("http://tracing-analysis-dc-hz.aliyuncs.com/adapt_f6yah647nw@42a790d7a35fc27_f6yah647nw@53df7ad2afe8301/api/traces");
        config.withSampler(new io.jaegertracing.Configuration.SamplerConfiguration().withType("const").withParam(1));
        config.withReporter(new io.jaegertracing.Configuration.ReporterConfiguration().withSender(sender).withMaxQueueSize(10000));
       if(!GlobalTracer.isRegistered()) GlobalTracer.register(config.getTracer());
    }
    /**
     * 封手机号接口
     */
    @Test
    public void banPhoneTest()
    {

        String phone="11111";
        assertEquals(bloomFilterPhone.banPhone(phone),true);
    }
    /**
     * 查询手机是否被封接口
     */
    @Test
    public void isPhoneBanedTest()
    {
        String phone="11111";
        assertEquals(bloomFilterPhone.isPhoneBaned(phone),true);
    }
    /**
     * 查询手机号是否存在封接口
     */
    @Test
    public void isPhoneExistTest()
    {
        String phone="11111";
        assertEquals(bloomFilterPhone.isPhoneExist(phone),false);
    }
    /**
     * 查询qq是否被封接口
     */
    @Test
    public void isQqBanedTest()
    {
        String qq="120515329432";
        assertEquals(bloomFilterPhone.isQqBaned(qq),false);
    }
    /**
     * 查询qq是否存在接口
     */
    @Test
    public void isQqExistTest()
    {
        String qq="11111";
        assertEquals(bloomFilterPhone.isQqExist(qq),false);
    }


}
