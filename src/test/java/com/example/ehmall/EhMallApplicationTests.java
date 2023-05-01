package com.example.ehmall;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.ehmall.controller.NlpController;
import com.example.ehmall.entity.Commodity;
import com.example.ehmall.mapper.CommodityMapper;
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
    @Autowired
    private CommodityMapper commodityMapper;
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
    public void reisGetDataTest() throws IOException, ClientException {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        BoundHashOperations<String, Object, Object> boundHashOperations=redisTemplate.boundHashOps("Commodity");
        Commodity tempCom=new Commodity();
        {                LambdaQueryWrapper<Commodity> lqw = new LambdaQueryWrapper<Commodity>();
            /**
             * 查询到id的实体
             */
            lqw.eq(Commodity::getId, 211);
            tempCom = commodityMapper.selectOne(lqw);
            if(tempCom!=null)
            {

                String userString=JSON.toJSONString(tempCom);
                boundHashOperations.put(String.valueOf(211),userString);
            }}
        Object aa= boundHashOperations.get(String.valueOf(211));
        if(aa!=null){
            System.out.println(21312);
        }
    }
    @Test
    public void reisPutDataTest() throws IOException, ClientException {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        BoundHashOperations<String, Object, Object> boundHashOperations=redisTemplate.boundHashOps("Commodity");
        Object aa= boundHashOperations.get(String.valueOf(110));
        if(aa!=null){
            System.out.println(21312);
        }
    }



}
