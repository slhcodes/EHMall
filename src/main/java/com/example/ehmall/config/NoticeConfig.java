package com.example.ehmall.config;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.ehmall.controller.PricingController;
import com.example.ehmall.entity.Commerce;
import com.example.ehmall.entity.Pricing;
import com.example.ehmall.entity.User;
import com.example.ehmall.mapper.CommerceMapper;
import com.example.ehmall.mapper.UserMapper;
import com.example.ehmall.util.SendMessageUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
@Component
public class NoticeConfig {

        @Resource
        private CommerceMapper commerceMapper;
        @Resource
        private UserMapper userMapper;
        @Resource
        private PricingController pricingController;
        @Scheduled(cron = "0 * * * * *") // 每十分钟执行一次
        public void execute() {
            LocalDateTime now = LocalDateTime.now();
            System.out.println(now);

            LocalDateTime targetTime = now.plusMinutes(50);
            System.out.println(targetTime);
            Date startTime = Date.from(targetTime.atZone(ZoneId.systemDefault()).toInstant());
            Date endTime = Date.from(targetTime.plus(Duration.ofMinutes(10)).atZone(ZoneId.systemDefault()).toInstant());
            System.out.println(endTime);
            List<Commerce> result = commerceMapper.selectByTimeRange(startTime, endTime);
            System.out.println("search  -------");
            SendMessageUtil.sendPostRequest("18233075330");
            for(Commerce i:result)
            {

                int sellerId=i.getSellerid();
                int buyerId=i.getBuyerid();
                LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
                lqw.eq(User::getId,sellerId);
                User sellUser = userMapper.selectOne(lqw);
                LambdaQueryWrapper<User> lqw1 = new LambdaQueryWrapper<User>();
                lqw1.eq(User::getId,buyerId);
                User buyUser = userMapper.selectOne(lqw1);
                String sellerPhone=sellUser.getPhone();
                String buyerPhone=buyUser.getPhone();
                if(sellerPhone!=null&&!"".equals(sellerPhone)&&sellerPhone.length()==11)
                {

                }
                if(buyerPhone!=null&&!"".equals(buyerPhone)&&buyerPhone.length()==11)
                {

                }
                Pricing a=new Pricing();
                a.setTime(new Date());
                a.setCommodityid(158);
                a.setUserid(5);
                a.setPrice(100.0);
                pricingController.insertPricing(a);
            }
            if (!result.isEmpty()) {
                System.out.println(result.get(0));
                System.out.println("Scheduled task running...");
            }
        }
    }
