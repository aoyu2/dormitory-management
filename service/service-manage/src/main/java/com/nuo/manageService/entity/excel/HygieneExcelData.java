package com.nuo.manageService.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class HygieneExcelData {


    /**
     * 楼栋号
     */
    @ExcelProperty(index = 0)
    private String buildingNo;

    /**
     * 宿舍号
     */
    @ExcelProperty(index = 1)
    private String dormitoryNo;

    /**
     * 卫生检查周期
     */
    @ExcelProperty(index = 2)
    private String week;

    /**
     * 卫生得分成绩
     */
    @ExcelProperty(index = 3)
    private Double grade;
}
