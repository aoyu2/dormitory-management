package com.nuo.ossService.controller;

import com.nuo.ossService.entity.FileVo;
import com.nuo.ossService.service.FileService;
import commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/fileoss")
public class OssController {

    @Autowired
    private FileService fileService;

    // 上传报修图片方法
    @PostMapping("/upload/RepairImg")
    public R uploadRepairImg(MultipartFile file){
        String url = fileService.uploadOss(file);
        // 封装，返回的是图片集合
        FileVo fileVo = new FileVo();
        fileVo.setName(UUID.randomUUID().toString());
        fileVo.setUrl(url);
        return R.ok().data("file",fileVo);
    }

    // 上传头像
    @PostMapping("/upload/Avatar")
    public R uploadAvatar(MultipartFile file){
        String url = fileService.uploadAvatar(file);
        return R.ok().data("imgSrc",url);
    }
}
