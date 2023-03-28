package com.nuo.repairService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuo.repairService.entity.RepairWorker;
import com.nuo.repairService.entity.TbRepair;
import com.nuo.repairService.entity.vo.BackRepairVo;
import com.nuo.repairService.entity.vo.RepairVo;
import com.nuo.repairService.mapper.RepairWorkerMapper;
import com.nuo.repairService.service.TbRepairService;
import com.nuo.repairService.mapper.TbRepairMapper;
import com.nuo.repairService.utils.DateTimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author Liu
* @description 针对表【tb_repair(保修信息表)】的数据库操作Service实现
* @createDate 2023-02-17 17:09:04
*/
@Service
public class TbRepairServiceImpl extends ServiceImpl<TbRepairMapper, TbRepair>
    implements TbRepairService{

    @Autowired
    private TbRepairMapper repairMapper;

    @Autowired
    private RepairWorkerMapper repairWorkerMapper;
    // 根据学生学号查找报修信息
    @Override
    public ArrayList<BackRepairVo> getRepairInfoByStudentId(String studentId) {
        QueryWrapper<TbRepair> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId).orderByDesc("gmt_create");
        List<TbRepair> repairList = baseMapper.selectList(queryWrapper);

        ArrayList<BackRepairVo> repairVos = new ArrayList<>();

        for (TbRepair tbRepair : repairList) {
            Integer id = tbRepair.getId();
            String studentName = tbRepair.getStudentName();
            Long phone = tbRepair.getPhone();
            String situation = tbRepair.getSituation();
            String state = tbRepair.getState();
            Date repairTime = tbRepair.getGmtCreate();

            // 创建BackRepairVo对象进行封装
            BackRepairVo backRepairVo = new BackRepairVo();
            backRepairVo.setId(id);
            backRepairVo.setStudentName(studentName);
            backRepairVo.setPhone(phone);
            backRepairVo.setSituation(situation);
            backRepairVo.setRepairTime(repairTime);
            backRepairVo.setState(Integer.parseInt(state));

            repairVos.add(backRepairVo);
        }

        return repairVos;
    }

    @Override
    public int getRepairTotal(Integer workerId) {
        int total = repairMapper.getRepairTotal(workerId);
        return total;
    }

    @Override
    public int getRepairToday(Integer workerId) {
        QueryWrapper<TbRepair> queryWrapper = new QueryWrapper<>();
        Date date = new Date();
        // 获取当天的起始时间
        Date beginOfDay = DateTimeUtils.getBeginOfDay(date);
        // 获取当天结束时间
        Date endOfDay = DateTimeUtils.getEndOfDay(date);
        queryWrapper.eq("worker_id",workerId).le("handle_time",endOfDay).ge("handle_time",beginOfDay);
        List<TbRepair> tbRepairs = baseMapper.selectList(queryWrapper);
        int size = tbRepairs.size();
        return size;
    }

    @Override
    public void completeRepair(Integer repairId,Integer workerId) {
        // 修改该维修信息
        repairMapper.completeRepair(repairId,new Date());
        // 将维修人员维修当前维修单数减一
        repairWorkerMapper.reduceCountOfRepair(workerId);
    }

    @Override
    public Integer getCompleteOfRepairCount(Integer workerId) {
        Integer count = repairMapper.getCompleteOfRepairCount(workerId);
        return count;
    }

    @Override
    public RepairVo getRepairInfoById(Integer repairId) {
        TbRepair tbRepair = baseMapper.selectById(repairId);
        // 真正返回对象，进行赋值封装
        RepairVo repairVo = new RepairVo();

        BeanUtils.copyProperties(tbRepair,repairVo);
        System.out.println(tbRepair);
        System.out.println(repairVo);
        // 判断是否已经处理
        if (repairVo.getState().equals("1") || repairVo.getState().equals("2")){
            // 获取维修人员信息
            RepairWorker repairWorker = repairWorkerMapper.selectById(repairVo.getWorkerId());
            // 进行赋值
            repairVo.setWorkerName(repairWorker.getName());
            // 工号
            repairVo.setWorkerNumber(repairWorker.getWorkerId());
            repairVo.setWorkerPhone(repairWorker.getPhone());
        }
        return repairVo;
    }
}




