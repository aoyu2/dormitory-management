package com.nuo.repairService.service;

import com.nuo.repairService.entity.RepairWorker;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nuo.repairService.entity.vo.WorkerVo;

/**
* @author Liu
* @description 针对表【repair_worker】的数据库操作Service
* @createDate 2023-03-24 16:27:38
*/
public interface RepairWorkerService extends IService<RepairWorker> {
    // 维修人员登录
    RepairWorker login(WorkerVo workerVo);

    // 获取当前维修量
    int getCurrentRepairOfCount(Integer workerId);
}
