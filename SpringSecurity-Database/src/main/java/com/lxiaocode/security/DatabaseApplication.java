package com.lxiaocode.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lixiaofeng
 * @date 2020/8/26 16:07
 */
@SpringBootApplication
@RestController
@RequestMapping("/")
public class DatabaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatabaseApplication.class);
    }

    @GetMapping("")
    public String index(){
        return "index.html";
    }
}
