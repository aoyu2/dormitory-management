package com.nuo.manageService.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
// 返回报修信息对象
public class RepairVo {
    /**
     * 报修id
     */
    private String id;

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
     * 是否已经处理（0，未处理  1，已处理）
     */
    private String state;

    /**
     * 维修人员id
     */
    private Integer workerId;

    /**
     * 维修人员姓名
     */
    private String repairWorker;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;
}
