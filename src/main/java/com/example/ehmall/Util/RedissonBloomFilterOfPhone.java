package com.example.ehmall.Util;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * redis布隆过滤器查询类
 * @author 施立豪
 * @time 2023/3/18
 */
public class RedissonBloomFilterOfPhone {
    /**
     * 查询电话号码是否已经注册
     * @param phone  电话号码
     * @return    返回是否注册结果
     * @author 施立豪
     */
    public static boolean IsPhoneExist(String phone)
    {
        Config config = new Config();

        String ip="redis://123.249.120.9";String pp1=":8083";
        String password="CUGerhuo",pp="333";
        config.useSingleServer().setAddress(ip+pp1);
        config.useSingleServer().setPassword(password+pp);
        //构造Redisson
        RedissonClient redisson = Redisson.create(config);
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("CugEHPhoneList");
        //初始化布隆过滤器：预计元素为100000L,误差率为0.1%

        bloomFilter.tryInit(100000L,0.001);

        //bloomFilter.add("100861");
        return bloomFilter.contains(phone);
//        return  true;
    }

    /**
     * 查询手机号是否被封
     * @param phone  待查手机号
     * @return   是否被封结果
     * @author 施立豪
     *
     */
    public static boolean IsPhoneBaned(String phone)
    {
        Config config = new Config();
        String ip="redis://123.249.120.9";String pp1=":8083";
        String password="CUGerhuo",pp="333";
        config.useSingleServer().setAddress(ip+pp1);
        config.useSingleServer().setPassword(password+pp);
        //构造Redisson
        RedissonClient redisson = Redisson.create(config);
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("CugEHBanedPhoneList");
        //初始化布隆过滤器：预计元素为100000L,误差率为0.1%
        bloomFilter.tryInit(100000L,0.001);
        return bloomFilter.contains(phone);
    }
    /**
     * 插入手机号列表的布隆过滤器
     * @param phone 待插手机号码
     * @return  返回是否插入成功
     * @author 施立豪
     */

    public static boolean InsertPhone(String phone)
    {
        Config config = new Config();
        String ip="redis://123.249.120.9";String pp1=":8083";
        String password="CUGerhuo",pp="333";
        config.useSingleServer().setAddress(ip+pp1);
        config.useSingleServer().setPassword(password+pp);
        //构造Redisson
        RedissonClient redisson = Redisson.create(config);
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("CugEHPhoneList");
        //初始化布隆过滤器：预计元素为100000L,误差率为0.1%
        bloomFilter.tryInit(100000L,0.001);
        bloomFilter.add(phone);
        return bloomFilter.contains(phone);
    }
    /**
     * 添加被封手机号到被封列表的布隆过滤器
     * @param phone 待封手机号码
     * @return  返回是否添加成功
     * @author 施立豪
     */
    public static boolean AddBanedPhone(String phone)
    {
        Config config = new Config();
        String ip="redis://123.249.120.9";String pp1=":8083";
        String password="CUGerhuo",pp="333";
        config.useSingleServer().setAddress(ip+pp1);
        config.useSingleServer().setPassword(password+pp);
        //构造Redisson
        RedissonClient redisson = Redisson.create(config);
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("CugEHBanedPhoneList");
        //初始化布隆过滤器：预计元素为100000L,误差率为0.1%
        bloomFilter.tryInit(100000L,0.001);
        bloomFilter.add(phone);
        return bloomFilter.contains(phone);
    }

}
