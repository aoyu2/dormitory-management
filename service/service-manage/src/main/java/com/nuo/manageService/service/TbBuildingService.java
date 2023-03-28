package com.nuo.manageService.service;

import com.nuo.manageService.entity.TbBuilding;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Liu
* @description 针对表【tb_building(楼栋表)】的数据库操作Service
* @createDate 2023-03-02 15:18:09
*/
public interface TbBuildingService extends IService<TbBuilding> {

    // 获取所有的楼栋号
    List<TbBuilding> getAllBuildingNo();
}
