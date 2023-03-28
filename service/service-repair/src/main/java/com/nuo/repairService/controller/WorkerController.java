package com.nuo.repairService.controller;

import com.nuo.repairService.entity.RepairWorker;
import com.nuo.repairService.entity.vo.WorkerVo;
import com.nuo.repairService.service.RepairWorkerService;
import commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private RepairWorkerService repairWorkerService;

    // 登录
    @PostMapping("/login")
    public R login(@RequestBody WorkerVo workerVo) {
        RepairWorker repairWorker = repairWorkerService.login(workerVo);
        if (repairWorker != null){
            return R.ok().data("item",repairWorker);
        }
        return R.error().message("账号或者密码错误");
    }

    // 获取当前维修量
    @GetMapping("/repair/count/{workerId}")
    public R getCurrentRepairOfCount(Integer workerId){
        int count = repairWorkerService.getCurrentRepairOfCount(workerId);
        return R.ok().data("count",count);
    }
}
