package com.nuo.repairService.entity.vo;

import lombok.Data;

@Data
public class DormitoryHygienismVo {

    /**
     * id
     */
    private String id;

    /**
     * 宿舍号
     */
    private String dormitoryNo;

    /**
     * 卫生成绩
     */
    private double grade;
}
