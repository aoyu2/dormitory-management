package com.nuo.repairService.service;

import com.nuo.repairService.entity.TbRepair;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nuo.repairService.entity.vo.BackRepairVo;
import com.nuo.repairService.entity.vo.RepairVo;

import java.util.ArrayList;

/**
* @author Liu
* @description 针对表【tb_repair(保修信息表)】的数据库操作Service
* @createDate 2023-02-17 17:09:04
*/
public interface TbRepairService extends IService<TbRepair> {


    // 根据学生学号查找报修信息
    ArrayList<BackRepairVo> getRepairInfoByStudentId(String studentId);

    // 根据维修人员id获取其维修总数
    int getRepairTotal(Integer workerId);

    // 根据维修人员id获取今日工单
    int getRepairToday(Integer workerId);

    // 完成维修
    void completeRepair(Integer repairId,Integer workerId);

    // 根据维修人员id获取其当前已完成的维修单数
    Integer getCompleteOfRepairCount(Integer workerId);

    // 根据维修id获取维修信息详情
    RepairVo getRepairInfoById(Integer repairId);
}
