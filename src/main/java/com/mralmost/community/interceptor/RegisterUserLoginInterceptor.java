package com.mralmost.community.interceptor;

import com.mralmost.community.model.RegisterUser;
import com.mralmost.community.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Lxj
 * @Package com.mralmost.community.interceptor
 * @Description TODO
 * @date: 2020/3/3
 */
@Configuration
public class RegisterUserLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RegisterUserService registerUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取用户登录态
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("register")) {
                    String username = cookie.getValue();
                    RegisterUser byUsername = registerUserService.findByUsername(username);
                    if (byUsername != null) {
                        request.getSession().setAttribute("userInfo", byUsername);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
