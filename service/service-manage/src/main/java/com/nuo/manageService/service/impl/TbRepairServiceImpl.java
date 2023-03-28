package com.nuo.manageService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuo.manageService.entity.TbRepair;
import com.nuo.manageService.entity.TbStudent;
import com.nuo.manageService.entity.custom.CustomIds;
import com.nuo.manageService.entity.custom.DistributionRepair;
import com.nuo.manageService.entity.vo.ReqHandle;
import com.nuo.manageService.entity.vo.ReqRepairVo;
import com.nuo.manageService.mapper.RepairWorkerMapper;
import com.nuo.manageService.service.TbRepairService;
import com.nuo.manageService.mapper.TbRepairMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
* @author Liu
* @description 针对表【tb_repair(保修信息表)】的数据库操作Service实现
* @createDate 2023-03-07 21:39:48
*/
@Service
public class TbRepairServiceImpl extends ServiceImpl<TbRepairMapper, TbRepair>
    implements TbRepairService{

    @Autowired
    private TbRepairMapper repairMapper;

    @Autowired
    private RepairWorkerMapper repairWorkerMapper;

    @Override
    public Page<TbRepair> getPageList(Page<TbRepair> page, ReqRepairVo reqRepairVo) {
        QueryWrapper<TbRepair> queryWrapper = new QueryWrapper<>();
        // 按id进行排序
        if ("asc".equals(reqRepairVo.getOrder())){
            // 升序
            queryWrapper.orderByAsc("id");
        }else{
            // 降序
            queryWrapper.orderByDesc("id");
        }
        queryWrapper.orderByDesc("gmt_create");
        // 如果条件为空
        if (reqRepairVo == null) {
            baseMapper.selectPage(page, queryWrapper);
            return page;
        }
        // 取值判断
        String studentName = reqRepairVo.getStudentName();
        String buildingNo = reqRepairVo.getBuildingNo();
        String dormitoryNo = reqRepairVo.getDormitoryNo();
        String repairProject = reqRepairVo.getRepairProject();
        // 0:(未维修)，1:(已维修)
        Integer state = reqRepairVo.getState();
        String begin = reqRepairVo.getBegin();
        String end = reqRepairVo.getEnd();

        if (!StringUtils.isEmpty(studentName)){
            queryWrapper.like("student_name","%"+studentName+"%");
        }

        if (!StringUtils.isEmpty(repairProject)){
            queryWrapper.like("first_project","%"+repairProject+"%");
        }

        if (!StringUtils.isEmpty(buildingNo)){
            queryWrapper.eq("building_no",buildingNo);
        }

        if (!StringUtils.isEmpty(dormitoryNo)){
            queryWrapper.eq("dormitory_no",dormitoryNo);
        }

        if (state != null && 1 == state) {
            queryWrapper.eq("state", state);
        }
        if (state != null && 0 == state) {
            queryWrapper.eq("state", state);
        }

        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_modified", end);
        }
        Page<TbRepair> repairPage = baseMapper.selectPage(page, queryWrapper);
        return repairPage;
    }

    // 处理报修
    @Override
    public void handleRepair(ReqHandle reqHandle) {
        repairMapper.handleRepair(reqHandle);
    }

    @Override
    public void distributionRepairWork() {
        // 查出维修人员id以及其当前负责的报修数量
        List<DistributionRepair> distributionRepairs = repairWorkerMapper.getDistributionRepairWork();
        // 查出当前未处理的报修数量
//        Integer NoRepairCount = repairMapper.selectNoRepairCount();
        // 查询出当前未处理的报修id
        List<CustomIds> ids = repairMapper.selectNoRepairIds();
        int repairSize = ids.size();

        int workerSize = distributionRepairs.size();
        for (DistributionRepair distributionRepair : distributionRepairs) {
            distributionRepair.setRepairId(new ArrayList<>());
        }
        // 调用方法，直到没有未处理的报修信息,每次分配一个报修信息
        while (repairSize > 0){
            // 按维修数量从大到小进行排序
            this.sortMethod(distributionRepairs);
            // 获取负责维修数量最少的维修人员信息
            Integer repairCount = distributionRepairs.get(workerSize - 1).getRepairCount();
            List<Integer> repairId = distributionRepairs.get(workerSize - 1).getRepairId();

            // 获取未报修信息id
            int tempId = ids.get(repairSize - 1).getId();
            // 进行分配
            repairId.add(tempId);
            distributionRepairs.get(workerSize - 1).setRepairCount(repairCount+1);
            // 移除以分配的未处理的报修信息id
            ids.remove(repairSize-1);
            // 未处理的报修信息数量减一
            repairSize--;
        }
//        System.out.println(distributionRepairs);
        // 将数据存入数据库
        repairWorkerMapper.saveRepairInfo(distributionRepairs);
        repairMapper.saveRepairInfo(distributionRepairs,new Date());
    }

    // 封装排序方法
    public void sortMethod(List<DistributionRepair> distributionRepairs){
        // 按维修数量从大到小进行排序
        distributionRepairs.sort(new Comparator<DistributionRepair>() {
            @Override
            public int compare(DistributionRepair o1, DistributionRepair o2) {
                return o2.getRepairCount() - o1.getRepairCount();
            }
        });
    }
}




