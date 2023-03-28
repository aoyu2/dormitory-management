package com.nuo.manageService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nuo.manageService.entity.TbStudent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nuo.manageService.entity.vo.ReqStudentVo;
import com.nuo.manageService.entity.vo.StudentVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @author Liu
* @description 针对表【tb_student(学生表)】的数据库操作Service
* @createDate 2023-03-02 12:16:13
*/
public interface TbStudentService extends IService<TbStudent> {

    // 根据楼栋号条件分页查询返回学生信息
    void getPageList(Page<TbStudent> studentPage, ReqStudentVo reqStudentVo);

    // 实现EasyExcel对Excel读操作
    void uploadByExcel(MultipartFile file, TbStudentService studentService);

    // 实现EasyExcel对Excel写操作
    String exportByExcel();

    // 修改学生信息
    void updateStudentInfo(TbStudent tbStudent);

    // 根据楼栋号以及宿舍号查询获取学生列表
    List<StudentVo> getStudentList(String buildingNo, String dormitoryNo);
}
