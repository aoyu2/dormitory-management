package com.nuo.repairService.entity.vo;

import lombok.Data;

import java.util.Date;

// 返回报修信息
@Data
public class BackRepairVo {
    /**
     * 维修信息id
     */
    private Integer id;

    /**
     * 报修人姓名
     */
    private String studentName;

    /**
     * 报修人电话
     */
    private Long phone;

    /**
     * 报修情况
     */
    private String situation;

    /**
     * 报修时间
     */
    private Date repairTime;

    /**
     * 是否已经维修（0，未维修；1，已维修）
     */
    private int state;
}
