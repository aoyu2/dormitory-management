package com.nuo.repairService.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
// 返回维修详情页面报修信息对象
public class RepairVo {
    /**
     * 学生学号
     */
    private String studentId;

    /**
     * 报修人姓名
     */
    private String studentName;

    /**
     * 报修人电话号码
     */
    private Long phone;

    /**
     * 宿舍号
     */
    private String dormitoryNo;

    /**
     * 楼栋号
     */
    private String buildingNo;

    /**
     * 报修情况
     */
    private String situation;

    /**
     * 报修图片
     */
    private String situationImg;

    /**
     * 一级报修项目
     */
    private String firstProject;

    /**
     * 二级报修项目
     */
    private String secondtProject;

    /**
     * 是否已经处理（0，未处理  1，已处理  2，已维修）
     */
    private String state;

    /**
     * 报修工单号 Long类型数据不正确？？？
     */
    private String workOrder;


    /**
     * 维修人员姓名
     */
    private String workerName;

    /**
     * 负责维修人员id
     */
    private Integer workerId;

    /**
     * 维修人员工号
     */
    private String workerNumber;

    /**
     * 维修人员电话
     */
    private Long workerPhone;

    /**
     * 处理时间
     */
    private Date handleTime;

    /**
     * 维修时间
     */
    private Date finishTime ;

    /**
     * 创建报修时间
     */
    private Date gmtCreate;
}
