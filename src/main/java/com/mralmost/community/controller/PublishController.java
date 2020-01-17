package com.mralmost.community.controller;

import com.mralmost.community.model.Question;
import com.mralmost.community.model.User;
import com.mralmost.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lxj
 * @Package com.mralmost.community.controller
 * @Description TODO
 * @date: 2020/1/12
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model) {

        //保存用户发布的内容,用于回显
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        //判空处理
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空!");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "描述不能为空!");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空!");
            return "publish";
        }

        //获取用户的登录态
        User user = (User) request.getSession().getAttribute("user");

        //当用户未登录时添加错误信息和发布的内容用于回显
        if (user == null) {
            model.addAttribute("error", "请先登录!");
            return "publish";
        }

        //发布问题
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtModified());
        questionService.insert(question);
        return "redirect:/";
    }
}
