package com.nuo.repairService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuo.repairService.entity.FirstProject;
import com.nuo.repairService.entity.SecondProject;
import com.nuo.repairService.entity.vo.FirstProjectVo;
import com.nuo.repairService.entity.vo.SecondProjectVo;
import com.nuo.repairService.mapper.SecondProjectMapper;
import com.nuo.repairService.service.FirstProjectService;
import com.nuo.repairService.mapper.FirstProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author Liu
* @description 针对表【first_project(一级报修项目表)】的数据库操作Service实现
* @createDate 2023-02-21 17:40:07
*/
@Service
public class FirstProjectServiceImpl extends ServiceImpl<FirstProjectMapper, FirstProject>
    implements FirstProjectService{

    @Autowired
    private SecondProjectMapper secondProjectMapper;


    // 查询所有一级项目以及其对应的二级的项目
    @Override
    public List<FirstProjectVo> getAllFirstAndSecondProject() {
        // 获取所有一级项目
        List<FirstProject> firstProjects = baseMapper.selectList(null);
        // 初始化返回集合
        ArrayList<FirstProjectVo> firstProjectVos = new ArrayList<>();

        // 遍历所有一级项目
        for (FirstProject firstProject : firstProjects) {
            String firstProjectId = firstProject.getId();
            // 创建FirstProjectVo对象进行赋值
            FirstProjectVo firstProjectVo = new FirstProjectVo();
            // 数据封装
            firstProjectVo.setId(firstProjectId);
            firstProjectVo.setProjectName(firstProject.getFirstProject());
            // 根据一级项目id匹配对应的二级项目
            QueryWrapper<SecondProject> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("first_id",firstProjectId);
            List<SecondProject> secondProjects = secondProjectMapper.selectList(queryWrapper);
            // 创建二级项目集合
            ArrayList<SecondProjectVo> secondProjectVos = new ArrayList<>();
            for (SecondProject secondProject : secondProjects) {
                SecondProjectVo secondProjectVo = new SecondProjectVo();
                secondProjectVo.setId(secondProject.getId());
                secondProjectVo.setProjectName(secondProject.getSecondProject());
                // 进行封装
                secondProjectVos.add(secondProjectVo);
            }

            // 进行最终封装
            firstProjectVo.setChildList(secondProjectVos);
            firstProjectVos.add(firstProjectVo);
        }
        return firstProjectVos;
    }
}




