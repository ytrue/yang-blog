package com.yang.blog.controller.backend;


import com.yang.blog.entity.Admin;
import com.yang.blog.service.IAdminService;
import com.yang.blog.util.QueryCondition;
import com.yang.blog.util.ResponseData;
import com.yang.blog.validate.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RequestMapping("admin")
@Controller
public class AdminController extends BasicController {

    @Autowired
    public IAdminService adminService;


    public AdminController() {
        super.loadUrlArr = new String[]{
                "/admin/auth/admin/index",
                "/admin/auth/admin/save",
                "/admin/auth/admin/edit"
        };
    }

    /**
     * 排序分页查询
     *
     * @param queryCondition
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/auth/admin/list")
    public Object list(QueryCondition queryCondition) {
        return adminService.queryPage(queryCondition);
    }


    /**
     * 添加数据
     *
     * @param admin
     * @return
     */
    @ResponseBody
    @PostMapping("/auth/admin/add")
    public ResponseData<Object> save(
            @RequestBody
            @Validated({Scene.Add.class}) Admin admin,
            BindingResult bindingResult
    ) {
        return adminService.add(admin, bindingResult);
    }

    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/auth/admin/find/{id}")
    public ResponseData<Map<String, Object>> find(@PathVariable("id") Long id) {
        return adminService.find(id);
    }


    /**
     * 修改数据
     *
     * @param admin
     * @return
     */
    @ResponseBody
    @PutMapping("/auth/admin/update")
    public ResponseData<Object> update(
            @Validated(Scene.Update.class)
            @RequestBody Admin admin,
            BindingResult bindingResult
    ) {
        return adminService.update1(admin, bindingResult);
    }


    /**
     * id 删除
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @DeleteMapping("/auth/admin/delete")
    public ResponseData<Object> delete(@RequestBody List<Long> ids) {
        return adminService.del(ids);
    }

    @GetMapping("/auth/admin/index")
    public String indexView() {
        return "backend/auth/admin/index";
    }

    @GetMapping("/auth/admin/save")
    public String saveView() {
        return "backend/auth/admin/save";
    }

    @GetMapping("/auth/admin/edit")
    public String editView() {
        return "backend/auth/admin/edit";
    }

}
