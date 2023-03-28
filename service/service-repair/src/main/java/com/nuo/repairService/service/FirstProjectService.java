package com.nuo.repairService.service;

import com.nuo.repairService.entity.FirstProject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nuo.repairService.entity.vo.FirstProjectVo;

import java.util.List;

/**
* @author Liu
* @description 针对表【first_project(一级报修项目表)】的数据库操作Service
* @createDate 2023-02-21 17:40:07
*/
public interface FirstProjectService extends IService<FirstProject> {

    // 查询所有一级项目以及其对应的二级的项目
    List<FirstProjectVo> getAllFirstAndSecondProject();
}
