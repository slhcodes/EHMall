package com.example.ehmall.service;

import com.example.ehmall.entity.Rating;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ehmall.entity.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author slh
 * @since 2023-04-30
 */
public interface RatingService extends IService<Rating> {
    /**
     * 插入评分数据，先判断有没有，有的话更新，没有则插入
     * @param rating 评分实体
     * @return  是否成功
     */
    public RespBean insertRating(Rating rating);


}
