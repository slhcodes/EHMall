package com.example.ehmall;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import com.example.ehmall.controller.NlpController;
import com.example.ehmall.entity.Commodity;
import com.example.ehmall.util.FuzzSearch;
import com.example.ehmall.util.NameUtil;
import com.example.ehmall.util.Nlp;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.Objects;

@SpringBootTest
class EhMallApplicationTests {

    @Autowired
    StringEncryptor encryptor;
    @Autowired
    private RedisTemplate <String, String>redisTemplate;
    @Test
    public void getPass() throws ClientException {
//        String url = encryptor.encrypt("123.249.120.9");
//        String name = encryptor.encrypt("root");
//        String password = encryptor.encrypt("CUGerhuo333");
//        System.out.println(url+"----------------");
//        System.out.println(name+"----------------");
//        System.out.println(password+"----------------");
//        for(int i=0;i<1000;++i){
//        System.out.println(NameUtil.getNickName());}
       // Nlp.getNlpWords("这是一段文本");

    }
    @Test
    public void fuzzSearchTest() throws IOException, ClientException {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        BoundHashOperations<String, Object, Object> boundHashOperations=redisTemplate.boundHashOps("Commodity");
        Object aa= boundHashOperations.get(String.valueOf(110));
        if(aa!=null){
            System.out.println(21312);
        }

    }


}
