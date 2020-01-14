package com.mralmost.community.controller;

import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.mapper.QuestionMapper;
import com.mralmost.community.model.User;
import com.mralmost.community.mapper.UserMapper;
import com.mralmost.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Lxj
 * @Package com.mralmost.controller
 * @Description TODO
 * @date: 2020/1/7
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        List<QuestionDTO> questionList = questionService.findAll();
        model.addAttribute("questionList" ,questionList);

        return "index";
    }
}
