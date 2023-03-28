package com.nuo.manageService.service;

import com.nuo.manageService.entity.AdminMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nuo.manageService.entity.vo.AdminVo;

/**
* @author Liu
* @description 针对表【admin_member(后台管理员表)】的数据库操作Service
* @createDate 2023-03-03 11:50:17
*/
public interface AdminMemberService extends IService<AdminMember> {
    // 管理员进行登录
    boolean login(AdminVo adminVo);
}
