package com.mralmost.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Lxj
 * @Package com.mralmost.community.interceptor
 * @Description TODO 配置拦截器
 * @date: 2020/1/17
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private GithubUserLoginInterceptor githubUserLoginInterceptor;

    @Autowired
    private RegisterUserLoginInterceptor registerUserLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册用户拦截器
        registry.addInterceptor(githubUserLoginInterceptor).addPathPatterns("/**");
        registry.addInterceptor(registerUserLoginInterceptor).addPathPatterns("/**");
    }
}
