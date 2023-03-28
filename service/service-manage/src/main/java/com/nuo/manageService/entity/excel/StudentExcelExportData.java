package com.nuo.manageService.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class StudentExcelExportData {
    /**
     * 学生姓名
     */
    @ExcelProperty("学生名字")
    private String name;

    /**
     * 性别
     */
    @ExcelProperty("性别")
    private String sex;

    /**
     * 学号
     */
    @ExcelProperty("学号")
    private String studentId;

    /**
     * 班级
     */
    @ExcelProperty("班级")
    private Integer classNo;

    /**
     * 电话
     */
    @ExcelProperty("电话")
    private String phone;

    /**
     * 楼栋号
     */
    @ExcelProperty("楼栋号")
    private String buildingNo;

    /**
     * 宿舍号
     */
    @ExcelProperty("宿舍号")
    private String dormitoryNo;

    /**
     * 床位号
     */
    @ExcelProperty("床位号")
    private Integer berth;
}
