package com.nuo.repairService.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.nuo.repairService.mapper")
public class RepairConfig {

}
