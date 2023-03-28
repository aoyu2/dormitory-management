package com.nuo.manageService.controller;

import com.nuo.manageService.entity.TbBuilding;
import com.nuo.manageService.service.TbBuildingService;
import commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/manage/building")
public class BuildingController {

    @Autowired
    private TbBuildingService buildingService;


    // 获取所有的楼栋号
    @GetMapping("/get/all")
    public R getAllBuildingNo(){
        List<TbBuilding> buildingList = buildingService.getAllBuildingNo();
        return R.ok().data("list",buildingList);
    }

    // 添加新的楼栋
    @PostMapping("/add/{buildingNo}")
    public R addBuilding(@PathVariable("buildingNo") String buildingNo){
        TbBuilding tbBuilding = new TbBuilding();
        tbBuilding.setBuildingNo(buildingNo);
        buildingService.save(tbBuilding);
        return R.ok();
    }
}
