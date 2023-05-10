package com.example.ehmall;

import com.example.ehmall.controller.RewardController;
import com.example.ehmall.controller.SearchController;
import com.example.ehmall.entity.Reward;
import io.opentracing.util.GlobalTracer;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EhMallApplication.class})
public class RewardTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private RewardController rewardController;
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
     * 插入悬赏
     * @throws IOException
     */
    @Test
    public void insertRewardTest() throws IOException {
        Reward a=new Reward();
        Integer c=5;
        a.setUserid(c);
        a.setDescription("想要一个保温水杯，最好是新的");
        a.setLocation("未来城校区");
        Date w=new Date(System.currentTimeMillis());
        a.setTime(w);
        a.setState(1);
        rewardController.insertReward(a);
    }
    /**
     * 获取悬赏
     */
    @Test
    public void getRewardTest()
    {
        System.out.println(rewardController.getRewards(1,10));
    }
    /**
     * 获取我的悬赏
     */
    @Test
    public void getMyRewardTest()
    {
        rewardController.getMyRewards(5);
    }

}
