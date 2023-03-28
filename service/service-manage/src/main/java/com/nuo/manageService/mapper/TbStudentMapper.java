package com.nuo.manageService.mapper;

import com.nuo.manageService.entity.TbStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Liu
* @description 针对表【tb_student(学生表)】的数据库操作Mapper
* @createDate 2023-03-02 12:16:13
* @Entity com.nuo.manageService.entity.TbStudent
*/
@Mapper
public interface TbStudentMapper extends BaseMapper<TbStudent> {

    // 更新学生信息
    void updateStudentInfo(@Param("tbStudent") TbStudent tbStudent);
}




