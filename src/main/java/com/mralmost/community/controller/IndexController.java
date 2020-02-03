package com.mralmost.community.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.exception.CustomException;
import com.mralmost.community.exception.ErrorCode;
import com.mralmost.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Lxj
 * @Package com.mralmost.controller
 * @Description TODO 显示首页信息的controller
 * @date: 2020/1/7
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    /**
     * 首页
     *
     * @param pageNum 页数
     * @return
     */
    @GetMapping(value = {"/{pageNum}", "/"})
    public String index(@PathVariable(name = "pageNum", required = false) Integer pageNum,
                        Model model) {
        //当第一次访问请求时pageNum未传入时设置为1
        if (pageNum == null || pageNum.equals("")) {
            pageNum = 1;
        }
        //设置起始页码和每页最大显示数量
        PageHelper.startPage(pageNum, 6);
        List<QuestionDTO> questionList = questionService.findAll();
        //当访问返回的数据为null时,显示异常信息
        if(questionList.size()==0){
            throw new CustomException(ErrorCode.QUESTION_NOT_FOUND);
        }
        //设置连续显示的页数
        PageInfo<QuestionDTO> pageInfo = new PageInfo<QuestionDTO>(questionList, 5);
        model.addAttribute("pageInfo", pageInfo);
        return "index";
    }
}
