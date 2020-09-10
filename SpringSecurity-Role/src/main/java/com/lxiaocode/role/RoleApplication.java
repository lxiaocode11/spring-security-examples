package com.lxiaocode.role;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lixiaofeng
 * @date 2020/9/10 上午8:43
 * @blog http://www.lxiaocode.com/
 */
@SpringBootApplication
@MapperScan("com.lxiaocode.role.mapper")
@RestController
@RequestMapping("")
public class RoleApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoleApplication.class, args);
    }

    @GetMapping("/user")
    public String user(){
        return "hello user";
    }

    @GetMapping("/admin")
    public String admin(){
        return "hello admin";
    }

    @GetMapping("/super-admin")
    public String superAdmin(){
        return "hello super admin";
    }
}
