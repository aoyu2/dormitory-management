package com.nuo.manageService.entity.vo;

import lombok.Data;

@Data
// 返回前端宿舍列表宿舍对象
public class DormitoryVo {
    /**
     * 宿舍id
     */
    private String id;

    /**
     * 宿舍号
     */
    private String dormitoryNo;

    /**
     * 宿舍人数
     */
    private Integer personCount;
}
