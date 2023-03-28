package com.nuo.manageService.entity.vo;

import lombok.Data;

@Data
// 条件分页查询学生信息，进行封装条件对象
public class ReqStudentVo {
    // 姓名
    private String name;

    // 学号
    private String studentId;

    // 班级
    private Integer classNo;

    // 楼栋号
    private String buildingNo;

    // 宿舍号
    private String dormitoryNo;
}
