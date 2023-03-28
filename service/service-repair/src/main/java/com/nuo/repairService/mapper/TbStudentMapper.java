package com.nuo.repairService.mapper;

import com.nuo.repairService.entity.TbStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Liu
* @description 针对表【tb_student(学生表)】的数据库操作Mapper
* @createDate 2023-02-17 21:25:27
* @Entity com.nuo.repair.entity.TbStudent
*/
@Mapper
public interface TbStudentMapper extends BaseMapper<TbStudent> {
    // 修改密码
    void updatePassword(@Param("studentId") String studentId,@Param("password") String password);
}




