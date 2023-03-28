package com.nuo.repairService.entity.vo;

import lombok.Data;

@Data
// 返回前端楼栋号实体
public class BuildingVo {
    // 楼栋号
    private String text;

    // 对应的楼栋序号
    private String value;
}
