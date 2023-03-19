package com.example.ehmall;

import com.example.ehmall.Util.RedissonBloomFilterOfPhone;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EhMallApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(RedissonBloomFilterOfPhone.IsPhoneExist("100861"));
    }

}
