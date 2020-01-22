package com.mralmost.community.controller;

import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Lxj
 * @Package com.mralmost.community.controller
 * @Description TODO 问题详情界面controller
 * @date: 2020/1/18
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * @param id    问题主键id
     * @param model
     * @return
     */
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model) {
        //获取问题信息
        QuestionDTO question = questionService.findById(id);
        question.setCreator(question.getUser().getId());
        model.addAttribute("question", question);
        return "question";
    }


}
