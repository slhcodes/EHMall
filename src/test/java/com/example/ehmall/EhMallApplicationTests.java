package com.example.ehmall;

import com.aliyuncs.exceptions.ClientException;
import com.example.ehmall.controller.NlpController;
import com.example.ehmall.util.FuzzSearch;
import com.example.ehmall.util.NameUtil;
import com.example.ehmall.util.Nlp;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class EhMallApplicationTests {

    @Autowired
    StringEncryptor encryptor;

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
        FuzzSearch.getUser();
    }


}
