package com.example.ehmall;

import com.example.ehmall.controller.UserInfoController;
import io.opentracing.util.GlobalTracer;
import org.testng.annotations.BeforeClass;

import com.example.ehmall.controller.UserController;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EhMallApplication.class})
public class UserInfoTest extends AbstractTestNGSpringContextTests{
    @Autowired
    private UserInfoController userInfoController;
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
     * 更换头像接口
     */
    @Test
    public void setImageUrlTest()
    {
        int id=5;
        String newUrl="IMG_4814.JPG";
        System.out.println(userInfoController.setImage(id,newUrl));
        assertEquals(userInfoController.setImage(id,newUrl).getMessage(),"成功");
        int id1=0;
        assertEquals(userInfoController.setImage(id1,newUrl).getMessage(),"失败");

    }
    /**
     * 更改性别接口
     */
    @Test
    public void setGenderTest()
    {
        int id=5;
        boolean gender=true;
        assertEquals( userInfoController.setGender(id,gender).getMessage(),"成功");
        int id1=0;
        assertEquals( userInfoController.setGender(id1,gender).getMessage(),"失败");


    }
    /**
     * 更改个签接口
     */
    @Test
    public void setSigTest()
    {
        int id=5;
        String sig="我是一个闲置回收爱好者";
        assertEquals( userInfoController.setSignature(id,sig).getMessage(),"成功");
        int id1=0;
        assertEquals( userInfoController.setSignature(id1,sig).getMessage(),"失败");
    }
    @Test
    public void setIntroTest()
    {
        int id=5;
        String sig="我是一个闲置回收爱好者";
        assertEquals( userInfoController.setIntro(id,sig).getMessage(),"成功");
        int id1=0;
        assertEquals( userInfoController.setIntro(id1,sig).getMessage(),"失败");
    }
    @Test
    public void setIntrestTest()
    {
        int id=5;
        String sig="闲置回收";
        assertEquals( userInfoController.setInterest(id,sig).getMessage(),"成功");
        int id1=0;
        assertEquals( userInfoController.setInterest(id1,sig).getMessage(),"失败");
    }
    @Test
    public void setAgeTest()
    {
        int id=5;
       int age=22;
        assertEquals( userInfoController.setAge(id,age).getMessage(),"成功");
        int id1=0;
        assertEquals( userInfoController.setAge(id1,age).getMessage(),"失败");
    }
    /**
     * 获取资料接口
     */
    @Test
    public void getInfoTest()
    {
        int id=5;
        assertEquals( userInfoController.getPartUserInfo(id).getUserName(),"shilihao");
        int id1=0;
        assertNull( userInfoController.getPartUserInfo(id1));

    }

}
