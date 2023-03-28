package com.nuo.repairService.service;

import com.nuo.repairService.entity.TbBuilding;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nuo.repairService.entity.vo.BuildingVo;

import java.util.List;

/**
* @author Liu
* @description 针对表【tb_building(楼栋表)】的数据库操作Service
* @createDate 2023-02-26 15:38:39
*/
public interface TbBuildingService extends IService<TbBuilding> {
    // 获取所有楼梯号信息,进行前端数据类型封装
    List<BuildingVo> getAllBuilding();


}
