package com.yang.blog.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yangyi
 * @date 2020/6/17 16:19
 * @description：系统配置控制器
 */
@RequestMapping("admin/system/config")
@Controller
public class ConfigController {
    //修改呢

    @GetMapping("index")
    public String indexView() {
        return "backend/system/config/index";
    }
}
