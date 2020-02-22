package com.mralmost.community.advice;

import com.alibaba.fastjson.JSON;
import com.mralmost.community.dto.ResultDTO;
import com.mralmost.community.exception.CustomException;
import com.mralmost.community.exception.ErrorCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Lxj
 * @Package com.mralmost.community.advice
 * @Description TODO 处理错误信息
 * @date: 2020/1/22
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler
    ModelAndView handler(HttpServletRequest request,
                         HttpServletResponse response,
                         Throwable e,
                         Model model) {
        String contentType = request.getContentType();
        //是json请求则返回错误信息,不是则返回错误页面
        if ("application/json".equals(contentType)) {
            ResultDTO resultDTO;
            if (e instanceof CustomException) {
                resultDTO = ResultDTO.errorOf((CustomException) e);
            } else {
                resultDTO = ResultDTO.errorOf(ErrorCode.SYSTEM_ERROR);
            }
            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.setStatus(200);
                PrintWriter out = response.getWriter();
                out.write(JSON.toJSONString(resultDTO));
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        } else {
            if (e instanceof CustomException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", ErrorCode.SYSTEM_ERROR);
            }
            return new ModelAndView("error");
        }
    }

}
