package com.mralmost.community.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mralmost.community.dto.NotificationDTO;
import com.mralmost.community.dto.QuestionDTO;
import com.mralmost.community.enums.NotificationEnum;
import com.mralmost.community.exception.CustomException;
import com.mralmost.community.exception.ErrorCode;
import com.mralmost.community.model.Notification;
import com.mralmost.community.model.User;
import com.mralmost.community.service.NotificationService;
import com.mralmost.community.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lxj
 * @Package com.mralmost.community.controller
 * @Description TODO 导航栏"我的问题"controller
 * @date: 2020/1/17
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;


    /**
     * 处理导航栏"我的问题"请求
     *
     * @param request
     * @param url     请求url
     * @param pageNum 请求页数
     * @param model
     * @return
     */
    @GetMapping("/profile/{url}/{pageNum}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "url") String url,
                          @PathVariable(name = "pageNum") String pageNum,
                          Model model) {
        //获取存储用户信息的cookie,为null时则返回首页并且显示提示信息
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "请先登录!");
            return "redirect:/";
        }
        //根据相应请求存储相应数据
        if ("questions".equals(url)) {
            //获取并存储界面数据信息
            PageHelper.startPage(Integer.parseInt(pageNum), 6);
            List<QuestionDTO> questionList;
            try {
                questionList = questionService.findByCreator(user.getId());
                PageInfo<QuestionDTO> pageInfo = new PageInfo<QuestionDTO>(questionList, 5);
                model.addAttribute("pageInfo", pageInfo);
                model.addAttribute("section", "questions");
                model.addAttribute("sectionName", "我的提问");
            } catch (Exception e) {
                //当访问返回的数据为null时,显示异常信息
                throw new CustomException(ErrorCode.QUESTION_NOT_FOUND);
            }
        } else if ("replies".equals(url)) {
            PageHelper.startPage(Integer.parseInt(pageNum), 6);
            List<Notification> notifications;
            try {
                notifications = notificationService.findAll(user.getId());
                PageInfo pageInfo = new PageInfo(notifications, 5);
                List<NotificationDTO> notificationDTOS = new ArrayList<>();
                //因为pageHelper存在线程问题,被迫把应该在service层写的东西搬到了controller,否则将导致分页数据错误
                for (Notification n : notifications) {
                    NotificationDTO notificationDTO = new NotificationDTO();
                    BeanUtils.copyProperties(n, notificationDTO);
                    notificationDTO.setTypeName(NotificationEnum.nameOfType(n.getType()));
                    notificationDTOS.add(notificationDTO);
                }
                pageInfo.setList(notificationDTOS);
                model.addAttribute("pageInfo", pageInfo);
                model.addAttribute("section", "replies");
                model.addAttribute("sectionName", "最新回复");
            } catch (Exception e) {
                throw new CustomException(ErrorCode.SYSTEM_ERROR);
            }
        }
        return "profile";
    }
}
