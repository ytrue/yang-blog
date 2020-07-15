package com.yang.blog.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping("about")
    public String index() {
        return "frontend/about";
    }
}
