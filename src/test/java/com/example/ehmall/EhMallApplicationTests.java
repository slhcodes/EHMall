package com.example.ehmall;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ehmall.controller.CommodityController;
import com.example.ehmall.controller.NlpController;
import com.example.ehmall.entity.Commodity;
import com.example.ehmall.entity.PartUserInfo;
import com.example.ehmall.entity.User;
import com.example.ehmall.mapper.CommodityMapper;
import com.example.ehmall.mapper.UserMapper;
import com.example.ehmall.service.impl.CommodityServiceImpl;
import com.example.ehmall.util.FuzzSearch;
import com.example.ehmall.util.NameUtil;
import com.example.ehmall.util.Nlp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@RunWith(SpringRunner.class)
@SpringBootTest
class EhMallApplicationTests {

    @Autowired
    StringEncryptor encryptor;
    @Autowired
    private RedisTemplate <String, String>redisTemplate;
    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    CommodityServiceImpl commodityService;
@Test
public void focusComTest()
{

    List<Integer> a=new ArrayList<>();
    for(int i=21;i<30;++i)
    {
        a.add(i);
    }
    a.add(4);a.add(5);a.add(23);
//    System.out.println("size"+commodityService.getFocusedCommodity(a,1).size());
}
    @Test
    public void selectPageTest(){
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.like(User::getUsername , "的");
        PageHelper.startPage(1, 10);
        List<User> list = userMapper.selectList(userLambdaQueryWrapper);
//用PageInfo对结果进行包装
        PageInfo page = new PageInfo(list);
        System.out.println(page.getPageNum());
        System.out.println(page.getPages());
//        Page<User> userPage = new Page<>(1 , 10);

//        System.out.println(userPage.getPages());
//        System.out.println(userPage.getTotal());
//        System.out.println("总页数： "+userIPage.getPages());
//        System.out.println("总记录数： "+userIPage.getTotal());
//        userIPage.getRecords().forEach(System.out::println);
    }
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
    public void reisGetAndPutCommodityDataTest() throws IOException, ClientException {
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
    public void reisGetUserDataTest() throws IOException, ClientException {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        BoundHashOperations<String, Object, Object> boundHashOperations=redisTemplate.boundHashOps("UserInfo");
        Object aa= boundHashOperations.get(String.valueOf(20));
        PartUserInfo tempUser=null;
        if(aa!=null){
            System.out.println(aa);
            tempUser = (PartUserInfo) JSON.parseObject(aa.toString(),PartUserInfo.class);
            System.out.println(tempUser.getId());
            System.out.println(tempUser.getUserName());
            System.out.println(tempUser.getSignature());
            System.out.println(tempUser.getImageUrl());
        }
    }



}
