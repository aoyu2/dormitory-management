package com.nuo.repairService.entity.vo;

import lombok.Data;

@Data
public class WorkerVo {
    /**
     * 工号(三位大写字母+3个数字组成)
     */
    private String account;

    /**
     * 维修人员密码
     */
    private String password;
}
