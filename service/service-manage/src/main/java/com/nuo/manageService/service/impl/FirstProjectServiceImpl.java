package com.nuo.manageService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuo.manageService.entity.FirstProject;
import com.nuo.manageService.service.FirstProjectService;
import com.nuo.manageService.mapper.FirstProjectMapper;
import org.springframework.stereotype.Service;

/**
* @author Liu
* @description 针对表【first_project(一级报修项目表)】的数据库操作Service实现
* @createDate 2023-03-12 15:43:06
*/
@Service
public class FirstProjectServiceImpl extends ServiceImpl<FirstProjectMapper, FirstProject>
    implements FirstProjectService{

}




