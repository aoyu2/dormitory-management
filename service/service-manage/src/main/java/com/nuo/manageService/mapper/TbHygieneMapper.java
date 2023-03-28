package com.nuo.manageService.mapper;

import com.nuo.manageService.entity.TbHygiene;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nuo.manageService.entity.vo.WeekVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Liu
* @description 针对表【tb_hygiene(卫生得分表)】的数据库操作Mapper
* @createDate 2023-03-12 15:55:16
* @Entity com.nuo.manageService.entity.TbHygiene
*/
@Mapper
public interface TbHygieneMapper extends BaseMapper<TbHygiene> {
    // 返回卫生得分周期
    List<String> getAllWeek();

    // 修改成绩
    void updateGrade(@Param("id") String id, @Param("value") double value);
}




