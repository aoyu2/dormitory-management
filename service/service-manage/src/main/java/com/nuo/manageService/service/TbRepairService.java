package com.nuo.manageService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nuo.manageService.entity.TbRepair;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nuo.manageService.entity.TbStudent;
import com.nuo.manageService.entity.vo.ReqHandle;
import com.nuo.manageService.entity.vo.ReqRepairVo;

/**
* @author Liu
* @description 针对表【tb_repair(保修信息表)】的数据库操作Service
* @createDate 2023-03-07 21:39:48
*/
public interface TbRepairService extends IService<TbRepair> {
    // 分页查询返回报修信息
    Page<TbRepair> getPageList(Page<TbRepair> page, ReqRepairVo reqRepairVo);

    // 处理报修
    void handleRepair(ReqHandle reqHandle);

    // 自动分配给维修工人所有未处理的报修信息
    void distributionRepairWork();
}
