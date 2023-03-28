package com.nuo.manageService.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nuo.manageService.entity.RepairWorker;
import com.nuo.manageService.entity.TbRepair;
import com.nuo.manageService.entity.vo.RepairVo;
import com.nuo.manageService.entity.vo.ReqHandle;
import com.nuo.manageService.entity.vo.ReqRepairVo;
import com.nuo.manageService.service.RepairWorkerService;
import com.nuo.manageService.service.TbRepairService;
import commonutils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/manage/repair")
public class RepairController {

    @Autowired
    private TbRepairService repairService;

    @Autowired
    private RepairWorkerService repairWorkerService;

    // 分页条件查询返回报修信息
    @PostMapping("/page/{pageNum}/{limit}")
    public R getPageList(@PathVariable("pageNum") Integer pageNum,
                         @PathVariable("limit") Integer limit,
                         @RequestBody ReqRepairVo reqRepairVo){
        Page<TbRepair> page = new Page<>(pageNum, limit);
        Page<TbRepair> repairPage =  repairService.getPageList(page,reqRepairVo);
        long total = repairPage.getTotal();
        List<TbRepair> records = repairPage.getRecords();
        // 处理数据
        ArrayList<RepairVo> repairVos = new ArrayList<>();
        for (TbRepair record : records) {
            Integer workerId = record.getWorkerId();
            //
            RepairVo repairVo = new RepairVo();
            BeanUtils.copyProperties(record,repairVo);
            String workerName = repairWorkerService.getNameById(workerId);
            repairVo.setRepairWorker(workerName);

            repairVos.add(repairVo);
        }
        return R.ok().data("rows",repairVos).data("total",total);
    }

    // 根据报修id查找报修信息
    @GetMapping("/get/{id}")
    public R getRepairInfo(@PathVariable("id") String id){
        TbRepair repair = repairService.getById(id);
        return R.ok().data("item",repair);
    }

    // 查询所有维修人员
    @GetMapping("/get/worker")
    public R gerAllRepairWorker(){
        List<RepairWorker> list = repairWorkerService.list();
        return R.ok().data("items",list);
    }

    // 处理报修
    @PostMapping("/handle")
    public R handleRepair(@RequestBody ReqHandle reqHandle){
        repairService.handleRepair(reqHandle);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")
    public R deleteRepairInfoById(@PathVariable("id") String id){
        boolean b = repairService.removeById(id);
        if (b){
            return R.ok();
        }
        return R.error().message("删除失败！");
    }

    // 自动分配给维修工人所有未处理的报修信息
    @GetMapping("/distribution")
    public R distributionRepairWork(){
        repairService.distributionRepairWork();
        return R.ok();
    }
}
