package com.nuo.repairService.controller;

import com.nuo.repairService.entity.TbStudent;
import com.nuo.repairService.entity.vo.StudentVo;
import com.nuo.repairService.service.TbStudentService;
import commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private TbStudentService studentService;

    // 学生登录
    @PostMapping("login")
    public R login(@RequestBody StudentVo studentVo){
        boolean result = studentService.login(studentVo);
        if (!result){
            return R.error().data("error","账号或者密码错误！");
        }
        // 登录成功，返回学生信息
        TbStudent student = studentService.getStudentInfo(studentVo);
        // 将密码设置为空
        student.setPassword("");
        return R.ok().data("studentInfo",student);
    }

    // 学生修改信息----登录之后，获取学号，进行信息修改
    @PostMapping( "update")
    public R saveInfo(@RequestBody TbStudent tbStudent){
        studentService.updateInfo(tbStudent);
        return R.ok();
    }

    // 根据学号查找学生信息
    @GetMapping("/get/{studentId}")
    public R getInfoByStudentId(@PathVariable("studentId") String studentId){
        TbStudent tbStudent = studentService.getInfoByStudentId(studentId);
        // 将密码设置为空
        tbStudent.setPassword("");
        return R.ok().data("studentInfo",tbStudent);
    }

    // 验证账号密码是否正确
    @PostMapping("/verify")
    public R verifyPwd(@RequestBody StudentVo studentVo){
        Boolean result = studentService.verifyPwd(studentVo);
        return R.ok().data("item",result);
    }

    // 修改密码
    @PostMapping("update/password")
    public R updatePassword(@RequestBody StudentVo studentVo){
        studentService.updatePassword(studentVo);
        return R.ok();
    }

    // 验证学号与手机号是否匹配
    @GetMapping("mate")
    public R mateStudentIdAndPhone(String studentID,String phone){
        Boolean result = studentService.mateStudentIdAndPhone(studentID,phone);
        return R.ok().data("item",result);
    }
}
