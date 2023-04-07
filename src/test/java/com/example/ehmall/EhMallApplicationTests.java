package com.example.ehmall;

import com.example.ehmall.Util.NameUtil;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EhMallApplicationTests {

    @Autowired
    StringEncryptor encryptor;

    @Test
    public void getPass() {
//        String url = encryptor.encrypt("123.249.120.9");
//        String name = encryptor.encrypt("root");
//        String password = encryptor.encrypt("CUGerhuo333");
//        System.out.println(url+"----------------");
//        System.out.println(name+"----------------");
//        System.out.println(password+"----------------");
        for(int i=0;i<1000;++i){
        System.out.println(NameUtil.getNickName());}
    }


}
