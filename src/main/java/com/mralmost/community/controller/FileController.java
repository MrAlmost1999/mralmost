package com.mralmost.community.controller;

import com.mralmost.community.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Lxj
 * @Package com.mralmost.community.controller
 * @Description TODO
 * @date: 2020/2/23
 */
@Controller
public class FileController {

    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDTO upload() {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/images/QQ.png");
        return fileDTO;
    }
}
