package com.mralmost.community.dto;

import com.mralmost.community.exception.CustomException;
import com.mralmost.community.exception.ErrorCode;
import lombok.Data;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Lxj
 * @Package com.mralmost.community.dto
 * @Description TODO
 * @date: 2020/2/5
 */
@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(ErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static ResultDTO errorOf(CustomException e) {
        return errorOf(e.getCode(), e.getMessage());
    }

    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }
}
