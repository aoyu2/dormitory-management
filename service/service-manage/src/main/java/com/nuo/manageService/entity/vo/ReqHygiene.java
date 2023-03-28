package com.nuo.manageService.entity.vo;

import lombok.Data;

@Data
// 前端查询周期楼栋卫生得分情况
public class ReqHygiene {
    /*
    周期
     */
    private String week;

    /*
    楼栋号
     */
    private String buildingNo;
}
