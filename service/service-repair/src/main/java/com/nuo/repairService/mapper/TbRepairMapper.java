package com.nuo.repairService.mapper;

import com.nuo.repairService.entity.TbRepair;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
* @author Liu
* @description 针对表【tb_repair(保修信息表)】的数据库操作Mapper
* @createDate 2023-02-17 17:09:04
* @Entity com.nuo.repair.entity.TbRepair
*/
@Mapper
public interface TbRepairMapper extends BaseMapper<TbRepair> {
    // 根据维修人员id获取其维修总数
    int getRepairTotal(Integer workerId);

    // 完成维修
    void completeRepair(@Param("repairId") Integer repairId, @Param("date") Date date);

    // 根据维修人员id获取其当前已完成的维修单数
    Integer getCompleteOfRepairCount(Integer workerId);
}




