package com.example.datascope;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.datascope.mapper")
@SpringBootApplication
public class DataScopeAuditApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataScopeAuditApplication.class, args);
    }
}

