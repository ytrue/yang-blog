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

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    public IAdminService adminService;

    /**
     * 排序分页查询
     *
     * @param queryCondition
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/auth/admin/list")
    public ResponseData<Object> list(QueryCondition queryCondition) {
        Object page = adminService.queryPage(queryCondition);
        return ResponseData.success(page);

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
