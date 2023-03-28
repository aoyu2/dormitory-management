package com.nuo.manageService.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nuo.manageService.entity.TbStudent;
import com.nuo.manageService.entity.vo.ReqStudentVo;
import com.nuo.manageService.entity.vo.StudentVo;
import com.nuo.manageService.service.TbStudentService;
import commonutils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/manage/student")
public class StudentController {

    @Autowired
    private TbStudentService studentService;

    // 根据楼栋号条件分页查询返回学生信息
    @PostMapping("/page/{pageNum}/{limit}")
    public R getPageList(@PathVariable("pageNum") Integer pageNum,
                         @PathVariable("limit") Integer limit,
                         @RequestBody ReqStudentVo reqStudentVo){
        Page<TbStudent> studentPage = new Page<>(pageNum, limit);
        studentService.getPageList(studentPage,reqStudentVo);
        long total = studentPage.getTotal();
        List<TbStudent> records = studentPage.getRecords();
        System.out.println(records);
        return R.ok().data("rows",records).data("total",total);
    }

    // 根据id删除学生信息
    @DeleteMapping("/delete/{id}")
    public R deleteStudent(@PathVariable("id") String id){
        studentService.removeById(id);
        return R.ok();
    }

    // 根据id返回学生信息
    @GetMapping("/get/{id}")
    public R getStudentInfo(@PathVariable("id") String id){
        TbStudent student = studentService.getById(id);
        StudentVo studentVo = new StudentVo();
        BeanUtils.copyProperties(student,studentVo);
        return R.ok().data("item",studentVo);
    }

    // 通过excel文件批量添加学生信息
    //实现EasyExcel对Excel读操作
    @PostMapping("/upload")
    public R uploadByExcel(MultipartFile file){
        studentService.uploadByExcel(file,studentService);
        return R.ok();
    }

    // 通过excel文件批量导出学生信息
    //实现EasyExcel对Excel写操作
    @GetMapping("/export")
    public R exportByExcel(){
        String exportInfo = studentService.exportByExcel();
        return R.ok().data("path",exportInfo);
    }

    // 添加单个学生信息
    @PostMapping("/add")
    public R addStudent(@RequestBody TbStudent tbStudent){
        tbStudent.setHeadImg(null);
        // 设置密码为学号后6位
        Long studentId = tbStudent.getStudentId();
        String s = studentId.toString();
        tbStudent.setPassword(s.substring(4));
        studentService.save(tbStudent);
        return R.ok();
    }

    // 根据id修改学生信息
    @PostMapping("/update")
    public R updateStudentInfo(@RequestBody TbStudent tbStudent){
//        studentService.updateStudentInfo(tbStudent);
        System.out.println(tbStudent);
        studentService.updateById(tbStudent);
        return R.ok();
    }

    // 根据楼栋号以及宿舍号查询获取学生列表
    @GetMapping("/get/students/{buildingNo}/{dormitoryNo}")
    public R getStudentList(@PathVariable("buildingNo") String buildingNo,
                            @PathVariable("dormitoryNo") String dormitoryNo){
        List<StudentVo> list = studentService.getStudentList(buildingNo,dormitoryNo);
        return R.ok().data("list",list);
    }
}
