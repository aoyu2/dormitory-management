package com.nuo.manageService.entity.vo;

import lombok.Data;

@Data
// 前端条件查询宿管对象
public class ReqHousemaster {

    /**
     * 宿管姓名
     */
    private String name;

    /**
     * 楼栋号
     */
    private String buildingNo;
}
