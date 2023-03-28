package com.nuo.manageService.entity.vo;

import lombok.Data;

@Data
// 管理员登录封装对象
public class AdminVo {
    /**
     * 管理员账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
