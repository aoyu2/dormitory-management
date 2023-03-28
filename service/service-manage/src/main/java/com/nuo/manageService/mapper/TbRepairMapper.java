package com.nuo.manageService.mapper;

import com.nuo.manageService.entity.TbRepair;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nuo.manageService.entity.custom.CustomIds;
import com.nuo.manageService.entity.custom.DistributionRepair;
import com.nuo.manageService.entity.vo.ReqHandle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author Liu
* @description 针对表【tb_repair(保修信息表)】的数据库操作Mapper
* @createDate 2023-03-07 21:39:48
* @Entity com.nuo.manageService.entity.TbRepair
*/
@Mapper
public interface TbRepairMapper extends BaseMapper<TbRepair> {

    // 处理报修
    void handleRepair(@Param("reqHandle") ReqHandle reqHandle);

    // 查出状态为1以及对应的维修人员id
    List<DistributionRepair> getDistributionRepairWork();

    // 查出当前未处理的报修数量
    Integer selectNoRepairCount();

    // 查询出当前未处理的报修id
    List<CustomIds> selectNoRepairIds();

    // 系统自动进行分配维修，修改状态为1
    void saveRepairInfo(@Param("distributionRepairs") List<DistributionRepair> distributionRepairs, @Param("date") Date date);
}




