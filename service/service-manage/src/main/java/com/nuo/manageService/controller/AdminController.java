package com.nuo.manageService.controller;

import com.nuo.manageService.entity.vo.AdminVo;
import com.nuo.manageService.service.AdminMemberService;
import commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage/admin")
public class AdminController {

    @Autowired
    private AdminMemberService adminMemberService;

    // 管理员进行登录
    @PostMapping("login")
    public R login(@RequestBody AdminVo adminVo){
        boolean result = adminMemberService.login(adminVo);
        if (result){
            return R.ok();
        }
        return R.error();
    }

    // 管理员进行登录
    @GetMapping("info")
    public R getInfo(){
        return R.ok().data("name","admin");
    }

    // 管理员退出登录
    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }
}
