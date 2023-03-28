package com.nuo.manageService.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class HygieneExcelExportData {

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
     * 得分
     */
    @ExcelProperty("得分")
    private Double grade;



}
