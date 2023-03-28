package com.nuo.manageService.service;

import com.nuo.manageService.entity.RepairWorker;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Liu
* @description 针对表【repair_worker】的数据库操作Service
* @createDate 2023-03-17 16:07:55
*/
public interface RepairWorkerService extends IService<RepairWorker> {
    // 根据id获取维修人员姓名
    String getNameById(Integer id);
}
