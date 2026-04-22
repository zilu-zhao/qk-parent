package com.qk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan//开启对Servlet组件的支持
@SpringBootApplication
public class QkManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(QkManagementApplication.class, args);
    }

}
