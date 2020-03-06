package com.mralmost.community.controller;

import com.mralmost.community.dto.AccessTokenDTO;
import com.mralmost.community.dto.GithubUserDTO;
import com.mralmost.community.dto.ResultDTO;
import com.mralmost.community.dto.UserDTO;
import com.mralmost.community.model.User;
import com.mralmost.community.provider.GithubProvider;
import com.mralmost.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class UserController {

    @Autowired
    private GithubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserService userService;

    /**
     * github回调方法(登录)
     *
     * @param code
     * @param state
     * @param response
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
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
            String token = UUID.randomUUID().toString();
            User user = new User();
            user.setCode(token);
            user.setUsername(githubUserDTO.getName());
            user.setPassword("github");
            user.setAccountId(String.valueOf(githubUserDTO.getId()));
            user.setAvatar(githubUserDTO.getAvatarUrl());
            Cookie cookie = new Cookie("accountId", String.valueOf(githubUserDTO.getId()));
            cookie.setMaxAge(60 * 60 * 24 * 30);
            cookie.setPath("/");
            response.addCookie(cookie);
            //首次登陆则添加,不是则根据实际情况修改用户信息
            userService.createOrUpdate(user);
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
        Cookie code = new Cookie("accountId", null);
        code.setMaxAge(0);
        code.setPath("/");
        response.addCookie(code);
        return "redirect:/";
    }

    /**
     * 去到登录界面
     *
     * @return
     */
    @GetMapping("/login")
    public String goLogin() {
        return "login";
    }

    /**
     * 注册用户 登录
     *
     * @param remember 是否记住该用户 true or false
     * @param username 用户账号
     * @param password 用户密码
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public ResultDTO login(@RequestParam("remember") boolean remember,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           HttpServletRequest request,
                           HttpServletResponse response,
                           Model model) {

        User user = userService.login(username, password);
        if (user == null) {
            //记住账号用于回显
            model.addAttribute("username", username);
            return ResultDTO.errorOf(500, "您输入的账号或密码有误,请重新输入");
        }
        if (remember) {
            //用于登录界面回显用户信息
            request.getSession().setAttribute("remember", user);
        }
        //用于标记用户登录状态
        Cookie cookie = new Cookie("accountId", user.getAccountId());
        cookie.setMaxAge(60 * 60 * 24 * 30);
        cookie.setPath("/");
        response.addCookie(cookie);

        //修改用户的最后一次登录时间
        userService.updateLastDate(user.getUsername());
        return ResultDTO.okOf();
    }

    /**
     * 去到注册界面
     *
     * @return
     */
    @GetMapping("/register")
    public String goRegister() {
        return "register";
    }

    /**
     * 激活|注册用户
     *
     * @param request
     * @param code
     * @param model
     * @return
     */
    @GetMapping("/activate_user")
    public String register(HttpServletRequest request,
                           @RequestParam("code") String code,
                           Model model) {
        //获取用户注册信息
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("registerUser");
        if (!code.equals(userDTO.getCode())) {
            model.addAttribute("error", "您的激活码有误请重新注册!");
            return "register";
        }
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userService.register(user);
        return "login";
    }
//

    /**
     * 验证用户是否存在
     *
     * @param username
     * @return
     */
    @ResponseBody
    @GetMapping("/exist_user")
    public ResultDTO existUser(@RequestParam("username") String username) {
        User user = userService.existUser(username);
        if (user != null) {
            return ResultDTO.errorOf(302, "您注册的用户名已经存在!");
        }
        return ResultDTO.okOf();
    }


}
