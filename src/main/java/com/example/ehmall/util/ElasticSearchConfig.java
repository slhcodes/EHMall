package com.example.ehmall.util;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @ClassName ElasticSearchConfig
 * @Description //ElasticSearchConfig
 * @Author TY
 * @Date 2021/1/26 10:05
 * @Version 1.0
 **/
@Configuration

public class ElasticSearchConfig {

    /**
     * 防止netty的bug
     * java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4]
     */
    @PostConstruct
    void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
}
