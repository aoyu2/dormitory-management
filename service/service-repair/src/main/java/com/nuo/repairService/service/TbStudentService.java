package com.nuo.repairService.service;

import com.nuo.repairService.entity.TbStudent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nuo.repairService.entity.vo.StudentVo;

/**
* @author Liu
* @description 针对表【tb_student(学生表)】的数据库操作Service
* @createDate 2023-02-17 21:25:27
*/
public interface TbStudentService extends IService<TbStudent> {

    // 学生注册
    boolean login(StudentVo studentVo);

    // 登录成功，返回学生信息
    TbStudent getStudentInfo(StudentVo studentVo);

    // 根据学号查找学生信息
    TbStudent getInfoByStudentId(String studentId);

    // 学生修改信息
    void updateInfo(TbStudent tbStudent);

    // 验证账号密码是否正确
    Boolean verifyPwd(StudentVo studentVo);

    // 修改密码
    void updatePassword(StudentVo studentVo);

    // 验证学号与手机号是否匹配
    Boolean mateStudentIdAndPhone(String studentId, String phone);
}
