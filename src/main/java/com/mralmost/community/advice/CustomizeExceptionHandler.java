package com.mralmost.community.advice;

import com.mralmost.community.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
                         Throwable e) {
        HttpStatus status = getStatus(request);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        if (e instanceof CustomException) {
            modelAndView.addObject("massage", ((CustomException) e).getMassage());
        } else {
            modelAndView.addObject("massage", "服务器冒烟了,要不你稍后再试试!");
        }
        return modelAndView;
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
