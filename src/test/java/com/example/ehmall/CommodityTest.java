package com.example.ehmall;
import com.example.ehmall.controller.*;
import com.example.ehmall.entity.Comment;
import com.example.ehmall.entity.Commodity;
import com.example.ehmall.entity.GetFocusRequest;
import com.example.ehmall.service.CommentService;
import io.opentracing.util.GlobalTracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
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
public class CommodityTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private CommodityController commodityController;
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
     * 查询商品接口
     */
    @Test
    public void getTest()
    {
        Integer temp=130;
        Commodity a=commodityController.getCommodity(130);
        assertEquals(a.getId(),temp);
        assertNull(commodityController.getCommodity(1));
    }
    /**
     * 获取关注商品列表接口
     */
    @Test
    public void getFocusComsTest()
    {
        GetFocusRequest getFocusRequest=new GetFocusRequest();
        getFocusRequest.setPage(1);
        getFocusRequest.setUsers(new int []{4,5,6});
        assertNotNull(commodityController.getFocusCommodities(getFocusRequest));
        getFocusRequest.setPage(0);
        assertNotNull(commodityController.getFocusCommodities(getFocusRequest));
    }
    @Test
    public void getMyPost()
    {
        commodityController.getMyRewards(5);
    }
    @Test
    public void updateCommodity()
    {
        Commodity com=new Commodity();
com.setId(110);
com.setCategory("平板电脑");
com.setBrand("Phone");
com.setPrice(560.0F);
com.setState(true);
com.setDescription("Phone8p  几乎全新 128G，功能流畅  无维修，手机无暗病，功能都好，指纹正常 ，成色新，拿来做备用机，工作机 很不错 \n");
com.setTime(new Date(System.currentTimeMillis()));
com.setQuality("几乎全新");
com.setUrl1("specific_CMP_20230429112019156.jpgspecific_CMP_20230429112019198.jpg;specific_CMP_20230429112019198.jpg");
com.setUserId(4);
        commodityController.updateCommodity(com);
    }

}
