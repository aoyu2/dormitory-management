package com.nuo.ossService.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.nuo.servicebase.config.exception.CustomException;
import com.nuo.ossService.service.FileService;
import com.nuo.ossService.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadOss(MultipartFile file) {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        // 填写Bucket名称
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;


        // 上传图片存储位置
        String uploadUrl = "";

        OSS ossClient = null;
        try {
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            if (!ossClient.doesBucketExist(bucketName)) {
                //创建bucket
                ossClient.createBucket(bucketName);
                //设置oss实例的访问权限：公共读
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            // 获取文件的输入流
            InputStream inputStream = file.getInputStream();

            // 获取文件上传的文件名
            String filename = file.getOriginalFilename();
            // 解决文件重名问题
            String hzName  = filename.substring(filename.lastIndexOf("."));
            String finalName = UUID.randomUUID().toString().replaceAll("-","");
            finalName = finalName + hzName;

            // 把文件按照日期进行分类1
            String datePath = new DateTime().toString("yyyy/MM/dd");
            finalName = datePath+finalName;

            // 创建PutObject请求。
            // 第一个参数是Bucket名称，第二个参数上传到oss文件路径和名称 aa/bb/1.jpg
            // 第三个参数上传文件输入流
            ossClient.putObject(bucketName, finalName, inputStream);

            // 拼接文件在阿里云上的路径
            // https://edu-model-01.oss-cn-hangzhou.aliyuncs.com/IMG_20220416_204408.jpg
            uploadUrl = "https://"+bucketName+".oss-cn-hangzhou.aliyuncs.com/"+finalName;
            return uploadUrl;
        } catch (Exception e) {
            throw new CustomException(44444, "文件上传失败");
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    @Override
    public String uploadAvatar(MultipartFile file) {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        // 填写Bucket名称
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;


        // 上传图片存储位置
        String uploadUrl = "";

        OSS ossClient = null;
        try {
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            if (!ossClient.doesBucketExist(bucketName)) {
                //创建bucket
                ossClient.createBucket(bucketName);
                //设置oss实例的访问权限：公共读
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            // 获取文件的输入流
            InputStream inputStream = file.getInputStream();

            // 获取文件上传的文件名
            String filename = file.getOriginalFilename();
            // 解决文件重名问题
            String hzName  = filename.substring(filename.lastIndexOf("."));
            String finalName = UUID.randomUUID().toString().replaceAll("-","");
            finalName = finalName + hzName;

            // 头像文件夹
            String filePath = "Avatar/";
            finalName = filePath+finalName;

            // 创建PutObject请求。
            // 第一个参数是Bucket名称，第二个参数上传到oss文件路径和名称 aa/bb/1.jpg
            // 第三个参数上传文件输入流
            ossClient.putObject(bucketName, finalName, inputStream);

            // 拼接文件在阿里云上的路径
            // https://edu-model-01.oss-cn-hangzhou.aliyuncs.com/IMG_20220416_204408.jpg
            uploadUrl = "https://"+bucketName+".oss-cn-hangzhou.aliyuncs.com/"+finalName;
            return uploadUrl;
        } catch (Exception e) {
            throw new CustomException(44444, "文件上传失败");
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
