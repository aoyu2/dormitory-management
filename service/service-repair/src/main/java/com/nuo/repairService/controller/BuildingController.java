package com.nuo.repairService.controller;


import com.nuo.repairService.entity.TbBuilding;
import com.nuo.repairService.entity.vo.BuildingVo;
import com.nuo.repairService.service.TbBuildingService;
import commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private TbBuildingService buildingService;

    // 获取所有楼梯号信息,进行前端数据类型封装
    @GetMapping("get")
    public R getAllBuilding(){
        List<BuildingVo> buildings = buildingService.getAllBuilding();
        return R.ok().data("list",buildings);
    }

}
