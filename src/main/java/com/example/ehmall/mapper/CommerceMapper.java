package com.example.ehmall.mapper;

import com.example.ehmall.entity.Commerce;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author slh
 * @since 2023-05-02
 */
@Mapper
public interface CommerceMapper extends BaseMapper<Commerce> {
    @Select("SELECT * FROM commerce WHERE time BETWEEN #{startTime} AND #{endTime}")
    List<Commerce> selectByTimeRange(Date startTime, Date endTime);



}
