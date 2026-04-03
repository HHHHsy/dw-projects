package com.dwproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebExhibitionApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebExhibitionApplication.class, args);
        System.out.println("========================================");
        System.out.println("网页展活动学习平台启动成功！");
        System.out.println("访问地址: http://localhost:8088");
        System.out.println("========================================");
    }
}
