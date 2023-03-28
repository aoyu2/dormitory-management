package com.nuo.repairService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.nuo")
public class RepairApplication {
    public static void main(String[] args) {
        SpringApplication.run(RepairApplication.class,args);
    }
}
