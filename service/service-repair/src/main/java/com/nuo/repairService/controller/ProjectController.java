package com.nuo.repairService.controller;

import com.nuo.repairService.entity.vo.FirstProjectVo;
import com.nuo.repairService.service.FirstProjectService;
import com.nuo.repairService.service.SecondProjectService;
import commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private FirstProjectService firstProjectService;

    @Autowired
    private SecondProjectService secondProjectService;


    // 查询所有一级项目以及其对应的二级的项目
    @GetMapping("/getAll")
    public R getAllFirstAndSecondProject(){
        List<FirstProjectVo> projects = firstProjectService.getAllFirstAndSecondProject();
        return R.ok().data("projects",projects);
    }
}
