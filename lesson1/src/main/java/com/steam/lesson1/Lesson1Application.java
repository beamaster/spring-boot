package com.steam.lesson1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@SpringBootApplication
public class Lesson1Application {

    @RequestMapping("/")
    String index() {
        return "Hello, Spring Boot"+ new Date();
    }

    public static void main(String[] args) {
        SpringApplication.run(Lesson1Application.class, args);
        System.out.println("hello,spring-boot");
    }
}
