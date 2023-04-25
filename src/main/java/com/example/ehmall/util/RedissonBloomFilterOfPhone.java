package com.example.ehmall.util;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis布隆过滤器查询类
 * @author 施立豪
 * @time 2023/3/18
 */
public class RedissonBloomFilterOfPhone {
    /**
     * redis连接
     */
    public static RedisTemplate redisTemplate = (RedisTemplate) SpringContextHolder.getBean("redisTemplate");
//    /**
//     * 查询电话号码是否已经注册
//     * @param phone  电话号码
//     * @return    返回是否注册结果
//     * @author 施立豪
//     */
//    public static boolean IsPhoneExist(String phone)
//    {
//        Config config = new Config();
//
//        String ip="redis://123.249.120.9";String pp1=":8083";
//        String password="CUGerhuo",pp="333";
//        config.useSingleServer().setAddress(ip+pp1);
//        config.useSingleServer().setPassword(password+pp);
//        //构造Redisson
//        RedissonClient redisson = Redisson.create(config);
//        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("CugEHPhoneList");
//        //初始化布隆过滤器：预计元素为100000L,误差率为0.1%
//
//        bloomFilter.tryInit(100000L,0.001);
//
//        //bloomFilter.add("100861");
//        return bloomFilter.contains(phone);
////        return  true;
//    }
    /**
     * 查询电话号码是否已经注册
     * @param phone  电话号码
     * @return    返回是否注册结果
     * @author 施立豪
     */
    public static boolean isPhoneExistre(String phone)
    {
/**
 * 先查用是否为set存储的1000个VIP用户，然后再查布隆过滤器
 */
        BoundSetOperations a=redisTemplate.boundSetOps("registerPhone");
        Long size=a.size();
        /**
         * vip用户查询
         */
        if(size<=1000)
        {
            return a.isMember(phone);
        }
        else
        {
            /**
             * 先查vip再查询布隆过滤器
             */
            if(a.isMember(phone)){return true;}
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
            return bloomFilter.contains(phone);
        }

    }

    /**
     * 查询手机号是否被封
     * @param phone  待查手机号
     * @return   是否被封结果
     * @author 施立豪
     *
     */
    public static boolean isPhoneBaned(String phone)
    {
        /**
         * 查询禁用列表
         */
        BoundSetOperations a=redisTemplate.boundSetOps("banedPhone");
            return a.isMember(phone);
    }
//    /**
//     * 插入手机号列表的布隆过滤器
//     * @param phone 待插手机号码
//     * @return  返回是否插入成功
//     * @author 施立豪
//     */
//    public static boolean InsertPhone(String phone)
//    {
//        Config config = new Config();
//        String ip="redis://123.249.120.9";String pp1=":8083";
//        String password="CUGerhuo",pp="333";
//        config.useSingleServer().setAddress(ip+pp1);
//        config.useSingleServer().setPassword(password+pp);
//        //构造Redisson
//        RedissonClient redisson = Redisson.create(config);
//        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("CugEHPhoneList");
//        //初始化布隆过滤器：预计元素为100000L,误差率为0.1%
//        bloomFilter.tryInit(100000L,0.001);
//        bloomFilter.add(phone);
//        return bloomFilter.contains(phone);
//    }
    /**
     * 插入手机号列表的布隆过滤器
     * @param phone 待插手机号码
     * @return  返回是否插入成功
     * @author 施立豪
     * @time 2023/3/20
     */
    public static boolean insertPhonere(String phone)
    {
        BoundSetOperations a=redisTemplate.boundSetOps("registerPhone");
        /**
         * 查询用户数是否超过1000，少于1000，插入vip-set中
         */
        Long size=a.size();
        if(size<=1000)
        {
        a.add(phone);
        return a.isMember(phone);
        }
        /**
         * 多于1000，插入布隆过滤器
         */
        else
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
    }
    /**
     * 添加被封手机号到被封列表的布隆过滤器
     * @param phone 待封手机号码
     * @return  返回是否添加成功
     * @author 施立豪
     */
    public static boolean addBanedPhone(String phone)
    {
        BoundSetOperations a=redisTemplate.boundSetOps("banedPhone");
        Long size=a.size();
            a.add(phone);
            return a.isMember(phone);
    }

}
