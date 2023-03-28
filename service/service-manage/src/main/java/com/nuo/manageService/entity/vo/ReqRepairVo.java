package com.nuo.manageService.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
// 报修条件查询对象
public class ReqRepairVo {
    /**
     * 报修学生名字
     */
    private String studentName;

    /**
     * 报修宿舍号
     */
    private String dormitoryNo;

    /**
     * 报修楼栋号
     */
    private String buildingNo;

    /**
     * 报修项目
     */
    private String repairProject;

    /**
     * 是否已经处理（0，未处理  1，已处理  2，已维修）
     */
    private Integer state;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;   //注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;

    /**
     * order 进行id排序（asc 升序； desc 降序）
     */
    private String order;
}
