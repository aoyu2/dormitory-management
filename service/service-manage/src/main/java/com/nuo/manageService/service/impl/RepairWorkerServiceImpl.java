package com.nuo.manageService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuo.manageService.entity.RepairWorker;
import com.nuo.manageService.service.RepairWorkerService;
import com.nuo.manageService.mapper.RepairWorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Liu
* @description 针对表【repair_worker】的数据库操作Service实现
* @createDate 2023-03-17 16:07:55
*/
@Service
public class RepairWorkerServiceImpl extends ServiceImpl<RepairWorkerMapper, RepairWorker>
    implements RepairWorkerService{

    @Autowired
    private RepairWorkerMapper repairWorkerMapper;

    @Override
    public String getNameById(Integer id) {
        String name = repairWorkerMapper.getNameById(id);
        return name;
    }
}




