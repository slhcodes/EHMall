package com.example.ehmall;
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
@SpringBootTest(classes = {EhMallApplication.class})
@RunWith(SpringRunner.class)
public class UserTest extends AbstractTestNGSpringContextTests {
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
    @Autowired
    private UserController userController;

    /**
     * 插入手机用户接口测试
     */
    @Test
    public void insertUserByPhoneTest(){

        String phone="13785957212";
        String username="世界之花";
        assertEquals(userController.insertUserByPhone(phone, username), true);
    }

    /**
     * 插入qq用户接口测试
     */
    @Test
    public void insertUserByQqTest(){
        String qq="137859572123213123434";
        String username="世界之花";
        assertEquals(userController.insertUserByQq(qq, username), true);
    }

    /**
     * 手机号封禁用户接口测试
     */
    @Test
    public void banUserPhoneTest(){
        String phone="13785957212";
        assertTrue(userController.banUserByPhone(phone));
        String phone1="121313785957212";
        assertTrue(userController.banUserByPhone(phone));
    }

    /**
     * qq封禁用户接口测试
     */
    @Test
    public void banUserQqeTest(){
        String qq="137859572123213123434";
        assertTrue(userController.banUserByQq(qq));
        String qq1="1378595721232131234341111";
        assertFalse(userController.banUserByQq(qq1));
    }
    /**
     * 根据手机获取id
     */
    @Test
    public void getIdByPhoneTest(){
        String phone="18233075330";
        String phone1="13756408658";
        String phone2="qeasdwa";
        String phone3="qeasdwa21312";

        assertEquals(userController.getIdByPhone(phone),5);
        assertEquals(userController.getIdByPhone(phone1),6);
        assertEquals(userController.getIdByPhone(phone2),-1);
        assertEquals(userController.getIdByPhone(phone3),-1);


    }
    /**
     * 根据qq获取id
     */
    @Test
    public void getIdByQqTest(){
        String qq="112231";
        String qq1="854464470FE55A0BEA1BA33A0258B02D";
        String qq2="154464470FE55A0BEA1BA33A0258B02D";
        String qq3="";
        assertEquals(userController.getIdByQq(qq),15);
        assertEquals(userController.getIdByQq(qq1),18);
        assertEquals(userController.getIdByQq(qq3),-1);
    }
}

