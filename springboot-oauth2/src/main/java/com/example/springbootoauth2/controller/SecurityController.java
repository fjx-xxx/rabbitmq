package com.example.springbootoauth2.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: Fangjx
 * @Date: 16:21  2023/4/4
 */
@Controller
//@PostMapping("/auth")
public class SecurityController {

    @Secured("ROLE_abc")
    @PostMapping("/auth")
    public String toMain(){
        System.out.println("登录成功！");
        return "redirect:main.html";
    }
}
