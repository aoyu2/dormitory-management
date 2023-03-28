package com.nuo.ossService.service;

import org.springframework.web.multipart.MultipartFile;


public interface FileService {

    /**
     * 文件上传到阿里云
     * @param file
     * @return
     */
    String uploadOss(MultipartFile file);

    // 上传头像
    String uploadAvatar(MultipartFile file);
}
