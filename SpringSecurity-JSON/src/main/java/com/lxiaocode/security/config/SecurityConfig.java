package com.lxiaocode.security.config;

import com.lxiaocode.common.ResponseUtil;
import com.lxiaocode.security.authentication.JsonAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lixiaofeng
 * @date 2020/8/25 22:04
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 所有请求都需要身份验证，关闭 CSRF
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

        // 配置 AuthenticationFilter
        UsernamePasswordAuthenticationFilter authenticationFilter =
                new JsonAuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());

        authenticationFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            Map<String, Object> map = new HashMap<String, Object>(3);
            map.put("code", 200);
            map.put("msg", "登陆成功");
            map.put("data", authentication.getPrincipal().toString());

            ResponseUtil.send(response, map);
        });

        authenticationFilter.setAuthenticationFailureHandler((request, response, exception) -> {
            Map<String, Object> map = new HashMap<String, Object>(3);

            map.put("code", 401);
            if (exception instanceof LockedException){
                map.put("msg", "账户被锁定");
            }else if (exception instanceof CredentialsExpiredException){
                map.put("msg", "密码过期");
            }else if (exception instanceof AccountExpiredException){
                map.put("msg", "账户过期");
            }else if (exception instanceof DisabledException){
                map.put("msg", "账户被禁用");
            }else if (exception instanceof BadCredentialsException){
                map.put("msg", "用户名或者密码输入错误");
            }
            map.put("data", exception.getMessage());

            ResponseUtil.send(response, map);
        });

        // 添加过滤器
        http.addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // 配置认证异常处理
        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            Map<String, Object> map = new HashMap<String, Object>(3);
            map.put("code", 401);
            map.put("message", "尚未登陆");
            map.put("data", authException.getMessage());

            ResponseUtil.send(response, map);
        });
    }
}
