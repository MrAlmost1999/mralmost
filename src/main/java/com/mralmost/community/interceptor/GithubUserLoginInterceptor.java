package com.mralmost.community.interceptor;

import com.mralmost.community.model.GithubUser;
import com.mralmost.community.service.GithubUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Lxj
 * @Package com.mralmost.community.interceptor
 * @Description TODO 用户登录拦截器
 * @date: 2020/1/17
 */
@Configuration
public class GithubUserLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private GithubUserService githubUserService;

//    @Autowired
//    private NotificationService notificationService;

    //执行前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取用户登录态
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    GithubUser githubUser = githubUserService.findByToken(token);
                    if (githubUser != null) {
                        request.getSession().setAttribute("userInfo", githubUser);
                        //未读通知
//                        Long unreadMessage = notificationService.unreadCount(byToken.getId());
//                        request.setAttribute("unreadMessage", unreadMessage);
                    }
                }
            }
        }
        return true;
    }

    //执行中
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //执行后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
