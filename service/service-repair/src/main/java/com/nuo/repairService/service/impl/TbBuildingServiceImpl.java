package com.nuo.repairService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuo.repairService.entity.TbBuilding;
import com.nuo.repairService.entity.vo.BuildingVo;
import com.nuo.repairService.service.TbBuildingService;
import com.nuo.repairService.mapper.TbBuildingMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author Liu
* @description 针对表【tb_building(楼栋表)】的数据库操作Service实现
* @createDate 2023-02-26 15:38:39
*/
@Service
public class TbBuildingServiceImpl extends ServiceImpl<TbBuildingMapper, TbBuilding>
    implements TbBuildingService{


    @Override
    // 获取所有楼梯号信息,进行前端数据类型封装
    public List<BuildingVo> getAllBuilding() {
        List<TbBuilding> buildings = baseMapper.selectList(null);
        ArrayList<BuildingVo> buildingVos = new ArrayList<>();

        for (TbBuilding building : buildings) {
            BuildingVo buildingVo = new BuildingVo();

            // 取出楼栋号
            String buildingNo = building.getBuildingNo();
            // 进行赋值
            buildingVo.setText(buildingNo);
            buildingVo.setValue(buildingNo);
            buildingVos.add(buildingVo);
        }
        return buildingVos;
    }

}




