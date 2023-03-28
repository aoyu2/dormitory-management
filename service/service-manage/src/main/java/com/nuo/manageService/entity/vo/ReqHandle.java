package com.nuo.manageService.entity.vo;

import lombok.Data;

@Data
public class ReqHandle {
    // 报修信息id
    private String repairId;

    // 维修人员Id
    private Integer workerId;
}
