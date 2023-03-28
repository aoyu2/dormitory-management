package com.nuo.repairService.service;

import com.nuo.repairService.entity.TbBuilding;
import com.nuo.repairService.entity.TbHygiene;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nuo.repairService.entity.vo.DormitoryHygienismVo;

import java.util.List;

/**
* @author Liu
* @description 针对表【tb_hygiene(卫生得分表)】的数据库操作Service
* @createDate 2023-02-26 20:36:03
*/
public interface TbHygieneService extends IService<TbHygiene> {
    //根据对应的楼栋号，获取其宿舍排名
    List<DormitoryHygienismVo> getDormitoryByBuildingNo(String buildingNo,String order);

}
