package com.mralmost.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Lxj
 * @Package com.mralmost.controller
 * @Description TODO
 * @date: 2020/1/7
 */
@Controller
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
