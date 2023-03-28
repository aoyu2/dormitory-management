package com.nuo.manageService.entity.custom;

import lombok.Data;

import java.util.List;

@Data
public class DistributionRepair {

    // 负责维修的人员id
    private Integer workerId;

    // 当前维修数量
    private Integer repairCount;

    // 将要分配的维修id
    private List<Integer> repairId;
}
