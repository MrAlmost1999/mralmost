package com.mralmost.community.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.service.QuestionService;
import com.mralmost.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/","index.html","index"})
    public String index(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                        Model model) {
        //设置起始页码和每页最大显示数量
        PageHelper.startPage(pageNum, 6);

        List<QuestionDTO> questionList = questionService.findAll();
        //设置连续显示的页数
        PageInfo<QuestionDTO> pageInfo = new PageInfo<QuestionDTO>(questionList,5);
        model.addAttribute("pageInfo", pageInfo);
        if (pageNum>pageInfo.getPages()){
            model.addAttribute("error","已经是最后一页了哦!");
        }
        return "index";
    }
}
