package com.cdfg.yjqr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.cdfg.yjqr.dao")
public class YjqrApplication {

    public static void main(String[] args) {
        SpringApplication.run(YjqrApplication.class, args);
    }

}
