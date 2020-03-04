package com.mralmost.community.controller;

import com.mralmost.community.dto.AccessTokenDTO;
import com.mralmost.community.dto.GithubUserDTO;
import com.mralmost.community.model.GithubUser;
import com.mralmost.community.provider.GithubProvider;
import com.mralmost.community.service.GithubUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author Lxj
 * @Package com.mralmost.controller
 * @Description TODO 保存用户信息和退出登录的controller
 * @date: 2020/1/9
 */
@Controller
@Slf4j
public class GithubUserController {

    @Autowired
    private GithubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private GithubUserService githubUserService;

    /**
     * 保存用户信息
     *
     * @param code
     * @param state
     * @param response
     * @return
     */
    @GetMapping("/githubCallback")
    public String githubCallback(@RequestParam(name = "code") String code,
                                 @RequestParam(name = "state") String state,
                                 HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GithubUserDTO githubUserDTO = gitHubProvider.getUser(accessToken);
        if (githubUserDTO != null && githubUserDTO.getId() != null) {
            //登录成功
            GithubUser githubUser = new GithubUser();
            String token = UUID.randomUUID().toString();
            githubUser.setToken(token);
            githubUser.setUsername(githubUserDTO.getUsername());
            githubUser.setAccountId(String.valueOf(githubUserDTO.getId()));
            githubUser.setAvatar(githubUserDTO.getAvatarUrl());
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 60 * 24 * 30);
            cookie.setPath("/");
            response.addCookie(cookie);
            //首次登陆则添加,不是则根据实际情况修改用户信息
            githubUserService.createOrUpdate(githubUser);
            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }
    }

    /**
     * 退出登录
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logOut")
    public String logOut(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("userInfo");
        Cookie token = new Cookie("token", null);
        Cookie register = new Cookie("register", null);
        token.setMaxAge(0);
        token.setPath("/");
        register.setMaxAge(0);
        register.setPath("/");
        response.addCookie(token);
        response.addCookie(register);
        return "redirect:/";
    }
}
