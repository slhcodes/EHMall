package com.example.ehmall.util;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis布隆过滤器查询类
 * @author 施立豪
 * @time 2023/3/18
 */
public class RedissonBloomFilterOfQq {
    /**
     * redis连接
     */
    private static RedisTemplate redisTemplate = (RedisTemplate) SpringContextHolder.getBean("redisTemplate");
//    /**
//     * 查询电话号码是否已经注册
//     * @param Qq  电话号码
//     * @return    返回是否注册结果
//     * @author 施立豪
//     */
//    public static boolean IsQqExist(String Qq)
//    {
//        Config config = new Config();
//
//        String ip="redis://123.249.120.9";String pp1=":8083";
//        String password="CUGerhuo",pp="333";
//        config.useSingleServer().setAddress(ip+pp1);
//        config.useSingleServer().setPassword(password+pp);
//        //构造Redisson
//        RedissonClient redisson = Redisson.create(config);
//        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("CugEHQqList");
//        //初始化布隆过滤器：预计元素为100000L,误差率为0.1%
//
//        bloomFilter.tryInit(100000L,0.001);
//
//        //bloomFilter.add("100861");
//        return bloomFilter.contains(Qq);
////        return  true;
//    }
    /**
     * 查询电话号码是否已经注册
     * @param qq  电话号码
     * @return    返回是否注册结果
     * @author 施立豪
     */
    public static boolean isQqExistre(String qq)
    {
/**
 * 先查用是否为set存储的1000个VIP用户，然后再查布隆过滤器
 */
        BoundSetOperations a=redisTemplate.boundSetOps("registerQq");
        Long size=a.size();
        /**
         * vip用户查询
         */
        if(size<=1000)
        {
            return a.isMember(qq);
        }
        else
        {
            /**
             * 先查vip再查询布隆过滤器
             */
            if(a.isMember(qq)){return true;}
            Config config = new Config();
            String ip="redis://123.249.120.9";String pp1=":8083";
            String password="CUGerhuo",pp="333";
            config.useSingleServer().setAddress(ip+pp1);
            config.useSingleServer().setPassword(password+pp);
            //构造Redisson
            RedissonClient redisson = Redisson.create(config);
            RBloomFilter<String> bloomFilter = redisson.getBloomFilter("CugEHQqList");
            //初始化布隆过滤器：预计元素为100000L,误差率为0.1%
            bloomFilter.tryInit(100000L,0.001);
            return bloomFilter.contains(qq);
        }

    }

    /**
     * 查询手机号是否被封
     * @param qq  待查手机号
     * @return   是否被封结果
     * @author 施立豪
     *
     */
    public static boolean isQqBaned(String qq)
    {
        /**
         * 查询禁用列表
         */
        BoundSetOperations a=redisTemplate.boundSetOps("banedQq");
            return a.isMember(qq);
    }
//    /**
//     * 插入手机号列表的布隆过滤器
//     * @param Qq 待插手机号码
//     * @return  返回是否插入成功
//     * @author 施立豪
//     */
//    public static boolean InsertQq(String Qq)
//    {
//        Config config = new Config();
//        String ip="redis://123.249.120.9";String pp1=":8083";
//        String password="CUGerhuo",pp="333";
//        config.useSingleServer().setAddress(ip+pp1);
//        config.useSingleServer().setPassword(password+pp);
//        //构造Redisson
//        RedissonClient redisson = Redisson.create(config);
//        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("CugEHQqList");
//        //初始化布隆过滤器：预计元素为100000L,误差率为0.1%
//        bloomFilter.tryInit(100000L,0.001);
//        bloomFilter.add(Qq);
//        return bloomFilter.contains(Qq);
//    }
    /**
     * 插入手机号列表的布隆过滤器
     * @param qq 待插手机号码
     * @return  返回是否插入成功
     * @author 施立豪
     * @time 2023/3/28
     */
    public static boolean insertQqre(String qq)
    {
        BoundSetOperations a=redisTemplate.boundSetOps("registerQq");
        /**
         * 查询用户数是否超过1000，少于1000，插入vip-set中
         */
        Long size=a.size();
        if(size<=1000)
        {
        a.add(qq);
        return a.isMember(qq);
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
            RBloomFilter<String> bloomFilter = redisson.getBloomFilter("CugEHQqList");
            //初始化布隆过滤器：预计元素为100000L,误差率为0.1%
            bloomFilter.tryInit(100000L,0.001);
            bloomFilter.add(qq);
            return bloomFilter.contains(qq);
        }
    }
    /**
     * 添加被封手机号到被封列表的布隆过滤器
     * @param qq 待封手机号码
     * @return  返回是否添加成功
     * @author 施立豪
     */
    public static boolean addBanedQq(String qq)
    {
        BoundSetOperations a=redisTemplate.boundSetOps("banedQq");
        Long size=a.size();
            a.add(qq);
            return a.isMember(qq);

    }

}
