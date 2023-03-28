package com.nuo.manageService.controller;

import com.nuo.manageService.entity.FirstProject;
import com.nuo.manageService.service.FirstProjectService;
import commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manage/project")
public class RepairProjectController {

    @Autowired
    private FirstProjectService projectService;

    // 获取所有一级报修项目列表
    @GetMapping("/get")
    public R getFirstProject(){
        List<FirstProject> list = projectService.list();
        return R.ok().data("list",list);
    }
}
