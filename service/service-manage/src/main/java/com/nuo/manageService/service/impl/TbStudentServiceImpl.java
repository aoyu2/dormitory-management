package com.nuo.manageService.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuo.manageService.entity.TbBuilding;
import com.nuo.manageService.entity.TbStudent;
import com.nuo.manageService.entity.excel.StudentExcelData;
import com.nuo.manageService.entity.excel.StudentExcelExportData;
import com.nuo.manageService.entity.vo.ReqStudentVo;
import com.nuo.manageService.entity.vo.StudentVo;
import com.nuo.manageService.listener.StudentListener;
import com.nuo.manageService.mapper.TbBuildingMapper;
import com.nuo.manageService.service.TbStudentService;
import com.nuo.manageService.mapper.TbStudentMapper;
import com.nuo.servicebase.config.exception.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
* @author Liu
* @description 针对表【tb_student(学生表)】的数据库操作Service实现
* @createDate 2023-03-02 12:16:13
*/
@Service
public class TbStudentServiceImpl extends ServiceImpl<TbStudentMapper, TbStudent>
    implements TbStudentService{

    @Autowired
    private TbStudentMapper studentMapper;

    @Autowired
    private TbBuildingMapper buildingMapper;

    // 根据楼栋号条件分页查询返回学生信息
    @Override
    public void getPageList(Page<TbStudent> studentPage, ReqStudentVo reqStudentVo) {
        // 取出数据
        String studentId = reqStudentVo.getStudentId();
        String name = reqStudentVo.getName();
        Integer classNo = reqStudentVo.getClassNo();
        String buildingNo = reqStudentVo.getBuildingNo();
        String dormitoryNo = reqStudentVo.getDormitoryNo();

        QueryWrapper<TbStudent> queryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(studentId)){
            queryWrapper.eq("student_id",studentId);
        }
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(classNo)){
            queryWrapper.eq("class_no",classNo);
        }
        if (!StringUtils.isEmpty(buildingNo)){
            queryWrapper.eq("building_no",buildingNo);
            if (!StringUtils.isEmpty(dormitoryNo)){
                queryWrapper.eq("dormitory_no",dormitoryNo);
            }
        }

        baseMapper.selectPage(studentPage, queryWrapper);
    }

    //实现EasyExcel对Excel读操作
    @Override
    public void uploadByExcel(MultipartFile file, TbStudentService studentService) {
        try {
            EasyExcel.read(file.getInputStream(), StudentExcelData.class, new StudentListener(studentService)).sheet().doRead();
        } catch (IOException e) {
            throw new CustomException(44444,"Excel表格读取错误！");
        }
    }

    // 实现EasyExcel对Excel写操作
    @Override
    public String exportByExcel() {
        // 查出所有楼栋号
        List<TbBuilding> tbBuildings = buildingMapper.selectList(null);

        // 方法2: 如果写到不同的sheet 同一个对象
        String fileName = "E://学生宿舍信息" + ".xlsx";
        // 这里 指定文件
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, StudentExcelExportData.class).build()) {
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
            for (TbBuilding tbBuilding : tbBuildings) {
                // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
                WriteSheet writeSheet = EasyExcel.writerSheet("楼栋"+tbBuilding.getBuildingNo()).build();
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                List<StudentExcelExportData> data = getStudentData(tbBuilding.getBuildingNo());
                excelWriter.write(data, writeSheet);
            }
        }
        return fileName;
    }

    // 根据楼栋号查找对应的学生信息
    public List<StudentExcelExportData> getStudentData(String buildingNo){
        QueryWrapper<TbStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("building_no",buildingNo).orderByAsc("class_no");
        List<TbStudent> tbStudents = baseMapper.selectList(queryWrapper);
        List<StudentExcelExportData> returnList = new ArrayList<>();
        // 进行封装赋值
        for (TbStudent tbStudent : tbStudents) {
            StudentExcelExportData exportData = new StudentExcelExportData();
            BeanUtils.copyProperties(tbStudent,exportData);
            // 赋值学号与电话
            exportData.setStudentId(String.valueOf(tbStudent.getStudentId()));
            exportData.setPhone(String.valueOf(tbStudent.getPhone()));
            returnList.add(exportData);
        }
        System.out.println(returnList);
        return returnList;
    }

    @Override
    public void updateStudentInfo(TbStudent tbStudent) {
        studentMapper.updateStudentInfo(tbStudent);
    }

    // 根据楼栋号以及宿舍号查询获取学生列表
    @Override
    public List<StudentVo> getStudentList(String buildingNo, String dormitoryNo) {
        List<StudentVo> studentVos = new ArrayList<StudentVo>();
        QueryWrapper<TbStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("building_no",buildingNo).eq("dormitory_no",dormitoryNo);
        List<TbStudent> tbStudents = baseMapper.selectList(queryWrapper);
        for (TbStudent tbStudent : tbStudents) {
            StudentVo studentVo = new StudentVo();
            BeanUtils.copyProperties(tbStudent,studentVo);
            studentVos.add(studentVo);
        }
        return studentVos;
    }
}




