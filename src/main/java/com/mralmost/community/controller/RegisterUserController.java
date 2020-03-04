package com.mralmost.community.controller;

import com.mralmost.community.dto.RegisterUserDTO;
import com.mralmost.community.dto.ResultDTO;
import com.mralmost.community.model.RegisterUser;
import com.mralmost.community.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Lxj
 * @Package com.mralmost.community.controller
 * @Description TODO 注册用户登录的controller
 * @date: 2020/3/2
 */
@Controller
public class RegisterUserController {

    @Autowired
    private RegisterUserService registerUserService;

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
     * 登录
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

        RegisterUser registerUser = registerUserService.login(username, password);
        if (registerUser == null) {
            //记住账号用于回显
            model.addAttribute("username", username);
            return ResultDTO.errorOf(500, "您输入的账号或密码有误,请重新输入");
        }
        if (remember) {
            //用于登录界面回显用户信息
            request.getSession().setAttribute("remember", registerUser);
        }
        //用于标记用户登录状态
        Cookie cookie = new Cookie("register", registerUser.getUsername());
        cookie.setMaxAge(60 * 60 * 24 * 30);
        cookie.setPath("/");
        response.addCookie(cookie);

        //修改用户的最后一次登录时间
        registerUserService.updateLastDate(registerUser.getUsername());
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
        RegisterUserDTO registerUserDTO = (RegisterUserDTO) request.getSession().getAttribute("registerUserDTO");
        if (!code.equals(registerUserDTO.getCode())) {
            model.addAttribute("error", "您的激活码有误请重新注册!");
            return "register";
        }
        registerUserService.register(registerUserDTO);
        return "login";
    }

    /**
     * 验证用户是否存在
     *
     * @param username
     * @return
     */
    @ResponseBody
    @GetMapping("/exist_user")
    public ResultDTO existUser(@RequestParam("username") String username) {
        RegisterUser registerUser = registerUserService.existUser(username);
        if (registerUser != null) {
            return ResultDTO.errorOf(302, "您注册的用户名已经存在!");
        }
        return ResultDTO.okOf();
    }


}
