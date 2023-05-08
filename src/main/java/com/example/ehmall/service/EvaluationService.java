package com.example.ehmall.service;

import com.example.ehmall.entity.Commerce;
import com.example.ehmall.entity.Evaluation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ehmall.entity.EvalutionInfo;
import com.example.ehmall.entity.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author slh
 * @since 2023-05-08
 */
public interface EvaluationService extends IService<Evaluation> {
    /**
     * 插入评价
     * @return
     */
    public RespBean insertCommodityId(Evaluation evaluation);
    /**
     * 获取未评价
     */
    public List<EvalutionInfo> getEvaluation(int userid);

}
