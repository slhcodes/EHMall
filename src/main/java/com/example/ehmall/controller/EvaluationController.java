
package com.example.ehmall.controller;


import com.example.ehmall.entity.*;
import com.example.ehmall.service.impl.EvaluationServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.elasticsearch.common.collect.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author slh
 * @since 2023-05-08
 */
@RestController
@Api(tags="订单评价接口")

@RequestMapping("/evaluation")
public class EvaluationController {
    @Autowired
    EvaluationServiceImpl evaluationService;
    @ApiOperation(value = "插入评价",notes = "成功返回true，失败返回false")
    @PostMapping("/insert")
    public RespBean insertcomment(@RequestBody Evaluation evaluation)
    {
        return evaluationService.insertCommodityId(evaluation);
    }
    @ApiOperation(value = "获取评价",notes = "成功返回评论实体列表，失败返回null")

    @GetMapping("/get")
    public   List<EvalutionInfo>getComment(@ApiParam(name="userid",required = true)
                               @RequestParam int userid)
    {
        return evaluationService.getEvaluation(userid);
    }

}
