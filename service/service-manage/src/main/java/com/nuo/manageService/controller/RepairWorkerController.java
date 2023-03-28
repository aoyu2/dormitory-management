package com.nuo.manageService.controller;

import com.nuo.manageService.entity.RepairWorker;
import com.nuo.manageService.service.RepairWorkerService;
import com.nuo.manageService.utils.RandOfWorkerId;
import commonutils.R;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage/worker")
public class RepairWorkerController {

    @Autowired
    private RepairWorkerService repairWorkerService;

    // 添加维修人员
    @PostMapping("/add")
    public R addNewRepairWorker(@RequestBody RepairWorker repairWorker){
        repairWorker.setWorkerId(RandOfWorkerId.createWorkerId());
        repairWorkerService.saveOrUpdate(repairWorker);
        return R.ok();
    }

    // 根据id获取维修人员信息
    @GetMapping("get/{id}")
    public R getRepairWorker(@PathVariable("id") Integer id){
        RepairWorker repairWorker = repairWorkerService.getById(id);
        return R.ok().data("item",repairWorker);
    }
    // 删除维修人员
    @DeleteMapping("/delete/{id}")
    public R deleteRepairWorker(Integer id){
        boolean b = repairWorkerService.removeById(id);
        if (b){
            return R.ok();
        }else {
            return R.error().message("删除失败");
        }

    }
}
