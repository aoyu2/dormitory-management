package com.nuo.manageService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuo.manageService.entity.TbDormitory;
import com.nuo.manageService.entity.vo.DormitoryVo;
import com.nuo.manageService.service.TbDormitoryService;
import com.nuo.manageService.mapper.TbDormitoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author Liu
* @description 针对表【tb_dormitory(宿舍表)】的数据库操作Service实现
* @createDate 2023-03-08 11:35:52
*/
@Service
public class TbDormitoryServiceImpl extends ServiceImpl<TbDormitoryMapper, TbDormitory>
    implements TbDormitoryService{

    @Autowired
    private TbDormitoryMapper dormitoryMapper;

    @Override
    public List<TbDormitory> getDormitoryList(String buildingNo) {
        QueryWrapper<TbDormitory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("building_no",buildingNo);
        List<TbDormitory> list = baseMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public Page<DormitoryVo> getPageList(Page<DormitoryVo> page, String buildingNo) {
        long current = page.getCurrent();
        long size = page.getSize();
        // 当前页
        current = (current-1)*size;
        List<DormitoryVo> dormitoryVos = dormitoryMapper.getDormitoryVos(buildingNo,current,size);
        long total= dormitoryMapper.getTotal(buildingNo);
        page.setRecords(dormitoryVos);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<DormitoryVo> getDormitoryVo(String dormitoryNo,String buildingNo) {
        DormitoryVo dormitoryVo =  dormitoryMapper.getDormitoryVo(dormitoryNo,buildingNo);
        List<DormitoryVo> list = new ArrayList<>();
        if (dormitoryVo == null){
            return null;
        }
        list.add(dormitoryVo);
        return list;
    }
}




