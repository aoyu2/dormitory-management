package com.nuo.ossService.entity;

import lombok.Data;

@Data
public class FileVo {
    // 图片唯一标识
    private String name;

    // 图片存储地址
    private String url;
}
