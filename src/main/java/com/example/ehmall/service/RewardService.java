package com.example.ehmall.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.ehmall.entity.Commodity;
import com.example.ehmall.entity.RespBean;
import com.example.ehmall.entity.Reward;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author slh
 * @since 2023-05-04
 */
public interface RewardService extends IService<Reward> {
    /**
     * 插入悬赏
     * @param reward 悬赏实体
     * @return
     */
    public RespBean insertReward(Reward reward);
    /**
     * 获取悬赏
     */
    public List<Reward> getReward(int page, int num);
    /**
     * 获取我的悬赏
     */
    public List<Reward> getMyReward(int userid);
}
