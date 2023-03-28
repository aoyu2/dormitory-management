package com.nuo.manageService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuo.manageService.entity.TbHousemaster;
import com.nuo.manageService.entity.TbStudent;
import com.nuo.manageService.entity.vo.ReqHousemaster;
import com.nuo.manageService.service.TbHousemasterService;
import com.nuo.manageService.mapper.TbHousemasterMapper;
import commonutils.R;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
* @author Liu
* @description 针对表【tb_housemaster(宿管表)】的数据库操作Service实现
* @createDate 2023-03-11 20:50:10
*/
@Service
public class TbHousemasterServiceImpl extends ServiceImpl<TbHousemasterMapper, TbHousemaster>
    implements TbHousemasterService{

    @Override
    public void getPageList(Page<TbHousemaster> housemasterPage, ReqHousemaster reqHousemaster) {

        QueryWrapper<TbHousemaster> queryWrapper = new QueryWrapper<>();
        // 取值
        String name = reqHousemaster.getName();
        String buildingNo = reqHousemaster.getBuildingNo();
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name","%"+name+"%");
        }

        if (!StringUtils.isEmpty(buildingNo)){
            queryWrapper.eq("building_no",buildingNo);
        }
        baseMapper.selectPage(housemasterPage, queryWrapper);
    }

}




