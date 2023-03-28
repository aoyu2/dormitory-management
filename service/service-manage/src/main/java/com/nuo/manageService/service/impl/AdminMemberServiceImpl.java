package com.nuo.manageService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuo.manageService.entity.AdminMember;
import com.nuo.manageService.entity.vo.AdminVo;
import com.nuo.manageService.service.AdminMemberService;
import com.nuo.manageService.mapper.AdminMemberMapper;
import com.nuo.manageService.utils.MD5;
import org.springframework.stereotype.Service;

/**
* @author Liu
* @description 针对表【admin_member(后台管理员表)】的数据库操作Service实现
* @createDate 2023-03-03 11:50:17
*/
@Service
public class AdminMemberServiceImpl extends ServiceImpl<AdminMemberMapper, AdminMember>
    implements AdminMemberService{

    @Override
    public boolean login(AdminVo adminVo) {
        // 取出数据
        String adminAccount = adminVo.getUsername();
        String password = adminVo.getPassword();

        // 对密码进行MD5加密
        password = MD5.encrypt(password);

        QueryWrapper<AdminMember> queryWrapper = new QueryWrapper<>();
        // 进行数据验证
        queryWrapper.eq("admin_account",adminAccount).eq("password",password);
        AdminMember adminMember = baseMapper.selectOne(queryWrapper);
        // 账号密码错误
        if (adminMember == null){
            return false;
        }
        return true;
    }
}




