package com.nuo.manageService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nuo.manageService.entity.TbHousemaster;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nuo.manageService.entity.vo.ReqHousemaster;

/**
* @author Liu
* @description 针对表【tb_housemaster(宿管表)】的数据库操作Service
* @createDate 2023-03-11 20:50:10
*/
public interface TbHousemasterService extends IService<TbHousemaster> {

    // 条件分页查询返回宿管信息
    void getPageList(Page<TbHousemaster> housemasterPage, ReqHousemaster reqHousemaster);
}
