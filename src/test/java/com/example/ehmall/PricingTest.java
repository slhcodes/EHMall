package com.example.ehmall;
import com.example.ehmall.controller.*;
import com.example.ehmall.entity.Comment;
import com.example.ehmall.entity.Pricing;
import com.example.ehmall.service.CommentService;
import com.example.ehmall.service.PricingService;
import io.opentracing.util.GlobalTracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
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
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EhMallApplication.class})
public class PricingTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private PricingController pricingController;
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
     * 出价接口
     */
    @Test
    public void commentTest()
    {
        Pricing a=new Pricing();
        a.setTime(new Date());
        a.setCommodityid(158);
        a.setUserid(5);
        a.setPrice(100.0);
        assertEquals(pricingController.insertPricing(a).getMessage(),"成功");
        a.setUserid(4);
        assertEquals(pricingController.insertPricing(a).getMessage(),"成功");
    }

    /**
     * 获取出价信息接口
     */
    @Test
    public void getCommentTest()
    {
        assertEquals(pricingController.getPricing(158).getMessage(),"success");
    }
}
