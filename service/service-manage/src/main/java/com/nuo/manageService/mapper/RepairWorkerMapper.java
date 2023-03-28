package com.nuo.manageService.mapper;

import com.nuo.manageService.entity.RepairWorker;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nuo.manageService.entity.custom.DistributionRepair;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Liu
* @description 针对表【repair_worker】的数据库操作Mapper
* @createDate 2023-03-17 16:07:55
* @Entity com.nuo.manageService.entity.RepairWorker
*/
@Mapper
public interface RepairWorkerMapper extends BaseMapper<RepairWorker> {
    // 根据id获取维修人员姓名
    String getNameById(Integer id);

    // 查出维修人员id以及其当前负责的报修数量
    List<DistributionRepair> getDistributionRepairWork();

    // 系统自动分配进行维修
    void saveRepairInfo(@Param("distributionRepairs") List<DistributionRepair> distributionRepairs);
}




