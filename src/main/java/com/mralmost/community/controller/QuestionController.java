package com.mralmost.community.controller;

import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.exception.CustomException;
import com.mralmost.community.exception.ErrorCode;
import com.mralmost.community.model.Record;
import com.mralmost.community.model.User;
import com.mralmost.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
    public String question(@PathVariable(name = "id") String id,
                           HttpServletRequest request,
                           Model model) {
        //获取问题信息
        QuestionDTO question = null;
        try {
            question = questionService.findById(Long.valueOf(id));
            model.addAttribute("question", question);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.QUESTION_NOT_FOUND);
        }

        //获取用户信息
        User user = (User) request.getSession().getAttribute("user");
        //用户已登录时累加阅读数量,未登录不做任何修改
        if (user != null) {
            Record record = new Record();
            record.setUserId(user.getId());
            record.setQuestionId(Long.valueOf(id));
            record.setRecordDate(new Date());
            questionService.updateViewCount(record);
        }
        return "question";
    }


}
