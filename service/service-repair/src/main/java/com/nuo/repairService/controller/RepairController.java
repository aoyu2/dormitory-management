package com.nuo.repairService.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nuo.repairService.entity.TbRepair;
import com.nuo.repairService.entity.vo.BackRepairVo;
import com.nuo.repairService.entity.vo.RepairVo;
import com.nuo.repairService.service.TbRepairService;
import com.nuo.repairService.utils.OrderNumberGenerator;
import commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/repair")
public class RepairController {

    @Autowired
    private TbRepairService repairService;

    // 学生提交报修信息
    @PostMapping("/publish")
    public R publishRepair(@RequestBody TbRepair tbRepair){
        // 生成工单号
        String workOrder = OrderNumberGenerator.generateOrderNumber();
//        Long workOrder = Long.parseLong(s);
        tbRepair.setWorkOrder(workOrder);
        // 保存报修信息
        repairService.save(tbRepair);
        return R.ok();
    }

    // 根据学生学号查找报修信息
    @GetMapping("/find/{studentId}")
    public R getRepairInfo(@PathVariable("studentId") String studentId){
        ArrayList<BackRepairVo> repairInfo = repairService.getRepairInfoByStudentId(studentId);
        return R.ok().data("list",repairInfo);
    }

    // 根据维修人员id返回其负责的所有维修信息
    @GetMapping("/get/{workId}")
    public R getAllRepairByWorkId(@PathVariable("workId") Integer workId){
        QueryWrapper<TbRepair> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("worker_id ",workId);
        List<TbRepair> list = repairService.list(queryWrapper);
        return R.ok().data("list",list);
    }

    // 根据维修人员id获取其维修总数
    @GetMapping("/total/{workerId}")
    public R getRepairTotal(@PathVariable("workerId") Integer workerId){
        int total = repairService.getRepairTotal(workerId);
        return R.ok().data("total",total);
    }

    // 根据维修人员id获取今日工单
    @GetMapping("/today/{workerId}")
    public R getRepairToday(@PathVariable("workerId") Integer workerId){
        int count = repairService.getRepairToday(workerId);
        return R.ok().data("count",count);
    }

    // 完成维修
    @PostMapping("/complete/{repairId}/{workerId}")
    public R completeRepair(@PathVariable("repairId") Integer repairId,
                            @PathVariable("workerId") Integer workerId){
        repairService.completeRepair(repairId,workerId);
        return R.ok();
    }

    // 根据维修人员id获取其当前已完成的维修单数
    @GetMapping("/complete/count/{workerId}")
    public R getCompleteOfRepairCount(@PathVariable("workerId") Integer workerId){
        Integer count = repairService.getCompleteOfRepairCount(workerId);
        return R.ok().data("count",count);
    }

    // 根据维修id获取维修信息详情
    @GetMapping("/info/{repairId}")
    public R getRepairInfoById(@PathVariable("repairId") Integer repairId){
        RepairVo repairVo = repairService.getRepairInfoById(repairId);
        return R.ok().data("item",repairVo);
    }
}
