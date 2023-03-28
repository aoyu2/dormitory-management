package com.nuo.manageService.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class StudentExcelData {

    /**
     * 学生姓名
     */
    @ExcelProperty(index = 0)
    private String name;

    /**
     * 性别
     */
    @ExcelProperty(index = 1)
    private String sex;

    /**
     * 学号
     */
    @ExcelProperty(index = 2)
    private Long studentId;

    /**
     * 班级
     */
    @ExcelProperty(index = 3)
    private Integer classNo;

    /**
     * 电话
     */
    @ExcelProperty(index = 4)
    private String phone;

    /**
     * 楼栋号
     */
    @ExcelProperty(index = 5)
    private String buildingNo;

    /**
     * 宿舍号
     */
    @ExcelProperty(index = 6)
    private String dormitoryNo;

    /**
     * 床位号
     */
    @ExcelProperty(index = 7)
    private Integer berth;
}
