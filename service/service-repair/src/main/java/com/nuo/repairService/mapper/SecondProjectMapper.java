package com.nuo.repairService.mapper;

import com.nuo.repairService.entity.SecondProject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Liu
* @description 针对表【second_project(二级报修项目表)】的数据库操作Mapper
* @createDate 2023-02-21 17:40:11
* @Entity com.nuo.repair.entity.SecondProject
*/
@Mapper
public interface SecondProjectMapper extends BaseMapper<SecondProject> {

}




