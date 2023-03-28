package com.nuo.repairService.controller;

import com.nuo.repairService.entity.TbBuilding;
import com.nuo.repairService.entity.vo.DormitoryHygienismVo;
import com.nuo.repairService.service.TbBuildingService;
import com.nuo.repairService.service.TbHygieneService;
import commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dormitory")
public class DormitoryHygienismController {

    @Autowired
    private TbHygieneService hygieneService;


    //根据对应的楼栋号，获取其宿舍排名
    @GetMapping("/get/{buildingNo}/{order}")
    public R getDormitoryByBuildingNo(@PathVariable("buildingNo") String buildingNo,
                                      @PathVariable("order") String order){
        List<DormitoryHygienismVo> hygieneList = hygieneService.getDormitoryByBuildingNo(buildingNo,order);
        return R.ok().data("list",hygieneList);
    }

}
