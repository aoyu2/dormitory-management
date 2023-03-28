package com.nuo.repairService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuo.repairService.entity.TbStudent;
import com.nuo.repairService.entity.vo.StudentVo;
import com.nuo.repairService.service.TbStudentService;
import com.nuo.repairService.mapper.TbStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @author Liu
* @description 针对表【tb_student(学生表)】的数据库操作Service实现
* @createDate 2023-02-17 21:25:27
*/
@Service
public class TbStudentServiceImpl extends ServiceImpl<TbStudentMapper, TbStudent>
    implements TbStudentService{

    @Autowired
    private TbStudentMapper studentMapper;

    // 进行登录验证
    @Override
    public boolean login(StudentVo studentVo) {
        QueryWrapper<TbStudent> queryWrapper = new QueryWrapper<>();

        // 取出学号，密码进行匹配
        String studentId = studentVo.getStudentId();
        String password = studentVo.getPassword();

        queryWrapper.eq("student_id",studentId).eq("password",password);
        TbStudent tbStudent = baseMapper.selectOne(queryWrapper);

        if (tbStudent == null){
            return false;
        }else{
            return true;
        }
    }

    // 登录成功，返回学生信息
    @Override
    public TbStudent getStudentInfo(StudentVo studentVo) {
        QueryWrapper<TbStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",studentVo.getStudentId());
        queryWrapper.eq("password",studentVo.getPassword());
        TbStudent student = baseMapper.selectOne(queryWrapper);
        return student;
    }

    // 根据学号查找学生信息
    @Override
    public TbStudent getInfoByStudentId(String studentId) {
        QueryWrapper<TbStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId);
        TbStudent tbStudent = baseMapper.selectOne(queryWrapper);
        return tbStudent;
    }

    @Override
    public void updateInfo(TbStudent tbStudent) {
        // 获取要修改信息
        Integer studentId = tbStudent.getStudentId();
        String name = tbStudent.getName();
        Long phone = tbStudent.getPhone();
        String dormitoryNo = tbStudent.getDormitoryNo();
        String buildingNo = tbStudent.getBuildingNo();

        if (name != null && !StringUtils.isEmpty(name)){
            // name不为空
        }

        baseMapper.updateById(tbStudent);
    }

    @Override
    public Boolean verifyPwd(StudentVo studentVo) {
        QueryWrapper<TbStudent> queryWrapper = new QueryWrapper<>();
        // 取出账号密码
        String studentId = studentVo.getStudentId();
        String password = studentVo.getPassword();
        queryWrapper.eq("student_id",studentId).eq("password",password);
        TbStudent tbStudent = baseMapper.selectOne(queryWrapper);
        if (tbStudent == null){
            return false;
        }
        return true;
    }

    // 修改密码
    @Override
    public void updatePassword(StudentVo studentVo) {
        String password = studentVo.getPassword();
        String studentId = studentVo.getStudentId();
        // 执行方法
        studentMapper.updatePassword(studentId,password);
    }

    @Override
    public Boolean mateStudentIdAndPhone(String studentId, String phone) {
        QueryWrapper<TbStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId).eq("phone",phone);

        TbStudent student = baseMapper.selectOne(queryWrapper);
        if (student == null){
            // 学号与匹配的手机号不一致，返回false
            return false;
        }
        return true;
    }
}




