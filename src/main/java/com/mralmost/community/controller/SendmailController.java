package com.mralmost.community.controller;

import com.mralmost.community.dto.UserDTO;
import com.mralmost.community.exception.CustomException;
import com.mralmost.community.exception.ErrorCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;
import java.util.UUID;

/**
 * @author Lxj
 * @Package com.mralmost.community.controller
 * @Description TODO 发送和处理邮件的controller
 * @date: 2020/3/3
 */
@Controller
public class SendmailController {

    /**
     * 发送邮件
     *
     * @param userDTO 接收到的注册信息
     */
    @PostMapping("/sendmail")
    public String sendmail(UserDTO userDTO,
                           HttpServletRequest request) {
        //激活码
        String code = UUID.randomUUID().toString();
        //邮件内容
        String content = "<h2>尊敬的用户" + userDTO.getUsername() + "你好,欢迎使用本站,激活请点击一下链接:</h2>" +
                "<h3><a href='http://mralmost.cn/activate_user?code=" + code + "'>激活账号</a></h3>";
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议，即：邮件协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        Transport transport = null;
        try {
            message.setFrom(new InternetAddress("2830114286@qq.com"));
            // 设置收件人邮箱地址
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(userDTO.getEmail())});
            // 设置邮件标题
            message.setSubject("请激活您的MrAlmost账号");
            // 设置邮件内容
            message.setContent(content, "text/html;charset=UTF-8");
            // 得到邮差对象
            transport = session.getTransport();
            // 连接自己的邮箱账户
            // 密码为QQ邮箱开通的stmp服务后得到的客户端授权码（你可以进入你的邮箱的设置里面查看）
            transport.connect("2830114286@qq.com", "scdyhmjtuasedcde");
            // 发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            userDTO.setCode(code);
            //存储用户注册信息
            request.getSession().setAttribute("registerUser", userDTO);
            return "redirect:/email";
        } catch (Exception e) {
            throw new CustomException(ErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * 去到等待用户验证邮件的界面
     *
     * @return
     */
    @GetMapping("/email")
    public String email() {
        return "email";
    }

}
