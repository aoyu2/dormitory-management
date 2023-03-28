package com.nuo.manageService.entity.vo;

import lombok.Data;

@Data
// 前端查看宿舍内居住的学生信息
public class StudentVo {

    /**
     * id
     */
    private String id;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 学号
     */
    private Long studentId;

    /**
     * 班级号
     */
    private Integer classNo;

    /**
     * 电话号码
     */
    private Long phone;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 宿舍号
     */
    private String dormitoryNo;

    /**
     * 床位号
     */
    private Integer berth;

    /**
     * 楼栋号
     */
    private String buildingNo;
}
