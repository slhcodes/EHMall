package com.example.ehmall;

import com.example.ehmall.controller.EvaluationController;
import com.example.ehmall.controller.RewardController;
import com.example.ehmall.controller.SearchController;
import com.example.ehmall.entity.Evaluation;
import com.example.ehmall.entity.Reward;
import io.opentracing.util.GlobalTracer;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EhMallApplication.class})
public class EvaluationTest extends AbstractTestNGSpringContextTests{
    @Autowired
    private EvaluationController evaluationMapper;
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

    @Test
    public void updateTest()
    {
        Evaluation evaluation=new Evaluation();
        evaluation.setCommerid(1);
        evaluation.setScore(1);
        Date a=new Date(System.currentTimeMillis());
        evaluation.setTime(a);
        evaluation.setUserid(5);
        evaluation.setState(1);
        evaluation.setContent("交易体验很好");
        assertEquals(evaluationMapper.updateEvaluation(evaluation).getCode(),200);
    }
    @Test
    public void selectTest()
    {
        int userid=5;
        assertNotNull(evaluationMapper.getComment(userid));
    }
    @Test
    public void insertTest()
    {
        int userid=5;
        Evaluation evaluation=new Evaluation();
        evaluation.setCommerid(1);
        evaluation.setScore(1);
        Date a=new Date(System.currentTimeMillis());
        evaluation.setTime(a);
        evaluation.setUserid(6);
        evaluation.setState(0);
        evaluation.setContent("交易体验很好");

        assertNotNull(evaluationMapper.updateEvaluation(evaluation));
    }
}
