package com.lxiaocode.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lixiaofeng
 * @date 2020/8/25 21:42
 */
public class JSONAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        // 判断 ContentType 类型
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)){

            // 获取请求内容
            Map<String, String> loginData = new HashMap<>(2);

            try {
                loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String username = loginData.get(getUsernameParameter());
            String password = loginData.get(getPasswordParameter());

            // 创建 Authentication
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, authentication);

            // 执行身份验证
            return this.getAuthenticationManager().authenticate(authentication);
        }else {
            return super.attemptAuthentication(request, response);
        }
    }
}
