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
@SpringBootTest(classes = {EhMallApplication.class})
public class CommerceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private CommerceController commerceController;
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
     * 插入交易信息接口
     */
    @Test
    public void insertTest()
    {

        Commerce a=new Commerce();
        a.setBuyerid(5);
        a.setSellerid(80);
        a.setCommodityid(115);
        a.setPrice(100.0);
        a.setTime(new Date());
        a.setState(0);
        assertEquals(commerceController.insertPricing(a).getMessage(),"成功");
        a.setPlace("未来图书馆门口");
        assertEquals(commerceController.insertPricing(a).getMessage(),"成功");
    }
    /**
     * 更新交易状态接口
     */
    @Test
    public void updateStateTest()
    {
        assertEquals(commerceController.getPricing(1,1).getMessage(),"成功");
        assertEquals(commerceController.getPricing(1,2).getMessage(),"成功");
        assertEquals(commerceController.getPricing(1,3).getMessage(),"成功");
        assertEquals(commerceController.getPricing(1,4).getMessage(),"成功");
        assertEquals(commerceController.getPricing(1,0).getMessage(),"成功");

    }
    /**
     * 获取交易接口
     */
    @Test
    public void getCommerceTest()
    {
        commerceController.getCommerce(132,5,6);

    }
    @Test
    public void getMyCommerceTest()
    {
        System.out.println(commerceController.getMyPricing(5).size());
    }


}
