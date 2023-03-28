package com.nuo.manageService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuo.manageService.entity.TbBuilding;
import com.nuo.manageService.service.TbBuildingService;
import com.nuo.manageService.mapper.TbBuildingMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Liu
* @description 针对表【tb_building(楼栋表)】的数据库操作Service实现
* @createDate 2023-03-02 15:18:09
*/
@Service
public class TbBuildingServiceImpl extends ServiceImpl<TbBuildingMapper, TbBuilding>
    implements TbBuildingService{

    @Override
    // 获取所有的楼栋号
    public List<TbBuilding> getAllBuildingNo() {
        QueryWrapper<TbBuilding> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("gmt_create");
        List<TbBuilding> buildingList = baseMapper.selectList(queryWrapper);
        return buildingList;
    }
}




