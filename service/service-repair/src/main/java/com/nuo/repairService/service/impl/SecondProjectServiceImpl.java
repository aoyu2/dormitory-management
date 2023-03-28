package com.nuo.repairService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuo.repairService.entity.SecondProject;
import com.nuo.repairService.service.SecondProjectService;
import com.nuo.repairService.mapper.SecondProjectMapper;
import org.springframework.stereotype.Service;

/**
* @author Liu
* @description 针对表【second_project(二级报修项目表)】的数据库操作Service实现
* @createDate 2023-02-21 17:40:11
*/
@Service
public class SecondProjectServiceImpl extends ServiceImpl<SecondProjectMapper, SecondProject>
    implements SecondProjectService{

}




