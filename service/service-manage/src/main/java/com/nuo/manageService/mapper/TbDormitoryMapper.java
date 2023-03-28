package com.nuo.manageService.mapper;

import com.nuo.manageService.entity.TbDormitory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nuo.manageService.entity.vo.DormitoryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Liu
* @description 针对表【tb_dormitory(宿舍表)】的数据库操作Mapper
* @createDate 2023-03-08 11:35:52
* @Entity com.nuo.manageService.entity.TbDormitory
*/
@Mapper
public interface TbDormitoryMapper extends BaseMapper<TbDormitory> {
    // 查询返回宿舍数据集合
    List<DormitoryVo> getDormitoryVos(@Param("buildingNo") String buildingNo,@Param("current") long current,@Param("size") long size);

    // 根据宿舍号返回DormitoryVo对象
    DormitoryVo getDormitoryVo(@Param("dormitoryNo") String dormitoryNo,@Param("buildingNo") String buildingNo);

    // 查出总数量
    long getTotal(@Param("buildingNo") String buildingNo);
}




