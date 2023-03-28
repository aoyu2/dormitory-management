package com.nuo.repairService.entity.vo;

import lombok.Data;

// 学生登录接收对象
@Data
public class StudentVo {

    /**
     * 学号（账号）
     */
    private String studentId;

    /**
     * 密码
     */
    private String password;
}
