//package com.mralmost.community.controller;
//
//import com.mralmost.community.dto.NotificationDTO;
//import com.mralmost.community.enums.NotificationEnum;
//import com.mralmost.community.service.NotificationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author Lxj
// * @Package com.mralmost.community.controller
// * @Description TODO 通知controller
// * @date: 2020/2/21
// */
//@Controller
//public class NotificationController {
//
//    @Autowired
//    private NotificationService notificationService;
//
//    /**
//     * 获取通知
//     *
//     * @param id      通知id
//     * @param request
//     * @param model
//     * @return
//     */
//    @GetMapping("/notification/{id}")
//    public String read(@PathVariable String id,
//                       HttpServletRequest request,
//                       Model model) {
//        //获取用户的登录态
//        User user = (User) request.getSession().getAttribute("user");
//        //当用户未登录时添加错误信息和发布的内容用于回显
//        if (user == null) {
//            model.addAttribute("error", "请先登录!");
//            return "redirect:/";
//        }
//
//        // 判空处理
//        NotificationDTO notificationDTO = notificationService.read(Long.valueOf(id), user);
//        if (NotificationEnum.REPLY_COMMENT.getType() == notificationDTO.getType() ||
//                NotificationEnum.REPLY_QUESTION.getType() == notificationDTO.getType()) {
//            return "redirect:/question/" + notificationDTO.getOuterId();
//        } else {
//            return "redirect:/";
//        }
//    }
//}
