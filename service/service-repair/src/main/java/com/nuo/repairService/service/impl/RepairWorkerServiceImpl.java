package com.nuo.repairService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuo.repairService.entity.RepairWorker;
import com.nuo.repairService.entity.vo.WorkerVo;
import com.nuo.repairService.service.RepairWorkerService;
import com.nuo.repairService.mapper.RepairWorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
* @author Liu
* @description 针对表【repair_worker】的数据库操作Service实现
* @createDate 2023-03-24 16:27:38
*/
@Service
public class RepairWorkerServiceImpl extends ServiceImpl<RepairWorkerMapper, RepairWorker>
    implements RepairWorkerService{

    @Autowired
    private RepairWorkerMapper repairWorkerMapper;

    @Override
    public RepairWorker login(WorkerVo workerVo) {
        QueryWrapper<RepairWorker> queryWrapper = new QueryWrapper<>();
        String password = workerVo.getPassword();
        String account = workerVo.getAccount();
        queryWrapper.eq("password",password);
        if (!StringUtils.isEmpty(account)){
            // 使用工号或者手机号登录
            queryWrapper.eq("worker_id",account).or().eq("phone",account);
        }

        RepairWorker worker = baseMapper.selectOne(queryWrapper);
        return worker;
    }

    @Override
    public int getCurrentRepairOfCount(Integer workerId) {
        Map<String, Integer> repairOfCount = repairWorkerMapper.getCurrentRepairOfCount(workerId);
        if (repairOfCount == null){
            return 0;
        }
        Integer count = repairOfCount.get("currentRepairCount");
        return count;
    }
}




