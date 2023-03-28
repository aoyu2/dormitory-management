package com.nuo.repairService.mapper;

import com.nuo.repairService.entity.RepairWorker;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
* @author Liu
* @description 针对表【repair_worker】的数据库操作Mapper
* @createDate 2023-03-24 16:27:38
* @Entity com.nuo.repairService.entity.RepairWorker
*/
@Mapper
public interface RepairWorkerMapper extends BaseMapper<RepairWorker> {
    // 将维修人员维修当前维修单数减一
    void reduceCountOfRepair(Integer workerId);

    // 获取当前维修量
    Map<String,Integer> getCurrentRepairOfCount(Integer workerId);

}




