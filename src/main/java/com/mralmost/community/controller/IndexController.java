package com.mralmost.community.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String index(@PathVariable(name = "pageNum", required = false) String pageNum,
                        @RequestParam(name = "search", required = false) String search,
                        Model model) {
        //当第一次访问请求时pageNum未传入时设置为1
        if (pageNum == null || pageNum.equals("")) {
            pageNum = "1";
        }
        //设置起始页码和每页最大显示数量
        PageHelper.startPage(Integer.parseInt(pageNum), 6);
        List<QuestionDTO> questionList = questionService.findQuestionWithUser(search);
        //当访问返回的数据为null时,显示异常信息
        if (questionList.size() == 0) {
            if (search != null && search.trim() != null) {
                model.addAttribute("questionNotFound", "你找的问题不存在或者已经删除了,要不再看看其他的吧?");
            } else {
                model.addAttribute("questionNotFound", "暂时还没有人发布问题哦,你要成为第一个发起人吗?");
            }
        } else {
            //设置连续显示的页数
            PageInfo<QuestionDTO> pageInfo = new PageInfo<QuestionDTO>(questionList, 5);
            model.addAttribute("pageInfo", pageInfo);
        }
        //最热问题和所有标签
        model.addAttribute("hottest", questionService.selectByHottestQuestion());
        model.addAttribute("newset", questionService.selectByNewsetQuestion());
        return "index";
    }
}
