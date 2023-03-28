package com.nuo.manageService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nuo.manageService.entity.TbDormitory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nuo.manageService.entity.vo.DormitoryVo;

import java.util.List;

/**
* @author Liu
* @description 针对表【tb_dormitory(宿舍表)】的数据库操作Service
* @createDate 2023-03-08 11:35:52
*/
public interface TbDormitoryService extends IService<TbDormitory> {
    // 根据楼栋号查询返回宿舍信息
    List<TbDormitory> getDormitoryList(String buildingNo);

    // 根据楼栋号分页查询返回宿舍特定信息
    Page<DormitoryVo> getPageList(Page<DormitoryVo> page, String buildingNo);

    // 根据宿舍号返回DormitoryVo对象
    List<DormitoryVo> getDormitoryVo(String dormitoryNo,String buildingNo);
}
