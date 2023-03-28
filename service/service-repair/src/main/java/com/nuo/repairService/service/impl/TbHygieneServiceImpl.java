package com.nuo.repairService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuo.repairService.entity.TbBuilding;
import com.nuo.repairService.entity.TbHygiene;
import com.nuo.repairService.entity.vo.DormitoryHygienismVo;
import com.nuo.repairService.service.TbHygieneService;
import com.nuo.repairService.mapper.TbHygieneMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
* @author Liu
* @description 针对表【tb_hygiene(卫生得分表)】的数据库操作Service实现
* @createDate 2023-02-26 20:36:03
*/
@Service
public class TbHygieneServiceImpl extends ServiceImpl<TbHygieneMapper, TbHygiene>
    implements TbHygieneService{

    //根据对应的楼栋号，获取其宿舍排名
    @Override
    public List<DormitoryHygienismVo> getDormitoryByBuildingNo(String buildingNo,String order) {
        QueryWrapper<TbHygiene> queryWrapper = new QueryWrapper<>();
        // 判断是升序还是降序
        if ("asc".equals(order)){
            queryWrapper.eq("building_no",buildingNo).orderByAsc("grade");
        }else if ("desc".equals(order)){
            queryWrapper.eq("building_no",buildingNo).orderByDesc("grade");
        }

        List<TbHygiene> hygienes = baseMapper.selectList(queryWrapper);

        ArrayList<DormitoryHygienismVo> hygienismVos = new ArrayList<>();

        for (TbHygiene hygiene : hygienes) {
            DormitoryHygienismVo hygienismVo = new DormitoryHygienismVo();
            hygienismVo.setId(UUID.randomUUID().toString());
            hygienismVo.setDormitoryNo(hygiene.getDormitoryNo());
            hygienismVo.setGrade(hygiene.getGrade());

            // 封装到集合中
            hygienismVos.add(hygienismVo);
        }
        return hygienismVos;
    }
}




