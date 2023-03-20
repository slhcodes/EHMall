package com.example.ehmall;

import io.opentracing.util.GlobalTracer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EhMallApplication {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                io.jaegertracing.Configuration config = new io.jaegertracing.Configuration("CUG贰货");
                io.jaegertracing.Configuration.SenderConfiguration sender = new io.jaegertracing.Configuration.SenderConfiguration();
                // 将 <endpoint> 替换为控制台概览页面上相应客户端和地域的接入点。
                sender.withEndpoint("http://tracing-analysis-dc-hz.aliyuncs.com/adapt_f6yah647nw@42a790d7a35fc27_f6yah647nw@53df7ad2afe8301/api/traces");
                config.withSampler(new io.jaegertracing.Configuration.SamplerConfiguration().withType("const").withParam(1));
                config.withReporter(new io.jaegertracing.Configuration.ReporterConfiguration().withSender(sender).withMaxQueueSize(10000));
                GlobalTracer.register(config.getTracer());
            }
        }).start();

        SpringApplication.run(EhMallApplication.class, args);
    }

}
