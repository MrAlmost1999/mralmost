package com.mralmost.community.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.model.User;
import com.mralmost.community.service.QuestionService;
import com.mralmost.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Lxj
 * @Package com.mralmost.community.controller
 * @Description TODO
 * @date: 2020/1/17
 */
@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile")
    public String profile(HttpServletRequest request,
                          @RequestParam(name = "url") String url,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          Model model) {
        Cookie[] cookies = request.getCookies();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".equals(url)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        } else if ("replies".equals(url)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        PageHelper.startPage(pageNum, 6);
        List<QuestionDTO> questionList = questionService.findByCreator(user.getId());
        PageInfo<QuestionDTO> pageInfo = new PageInfo<QuestionDTO>(questionList,5);
        model.addAttribute("pageInfo", pageInfo);
        return "profile";
    }
}