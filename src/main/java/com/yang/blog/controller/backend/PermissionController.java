package com.yang.blog.controller.backend;

import com.yang.blog.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;

/**
 * @author：yangyi
 * @date：2020/5/23 14:11
 * @description：
 */

class PermissionController extends BasicController {


    @GetMapping("/admin/auth/permission/index")
    public String test(Map<String, Object> map) {
        return "backend/auth/admin/index";
    }
}
