package com.yang.blog.controller.backend;

import com.yang.blog.entity.Permission;
import com.yang.blog.service.IPermissionService;
import com.yang.blog.service.IRoleMenuService;
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
@RequestMapping("admin/auth/menu")
public class MenuController {

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRoleMenuService roleMenuService;

    /**
     * 获得所有的权限
     */
    @ResponseBody
    @PostMapping(value = "all")
    public Map<String, Object> all() {
        return permissionService.all();
    }


    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping(value = "menu/{id}")
    public ResponseData<Map<String, Object>> find(@PathVariable("id") Long id) {
        return permissionService.find(id);
    }

    /**
     * 添加数据
     *
     * @param permission
     * @return
     */
    @ResponseBody
    @PostMapping("add")
    public ResponseData<Object> save(
            @RequestBody
            @Validated({Scene.Add.class}) Permission permission,
            BindingResult bindingResult
    ) {
        return permissionService.add(permission, bindingResult);
    }


    /**
     * 修改数据
     *
     * @param permission
     * @return
     */
    @ResponseBody
    @PutMapping("update")
    public ResponseData<Object> update(
            @Validated
            @RequestBody Permission permission,
            BindingResult bindingResult
    ) {
        return permissionService.update1(permission, bindingResult);
    }


    /**
     * id 删除
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @DeleteMapping("delete")
    public ResponseData<Object> delete(@RequestBody List<Long> ids) {
        return permissionService.del(ids);
    }

    @GetMapping("index")
    public String indexView() {
        return "backend/auth/menu/index";
    }

    @GetMapping("add")
    public String saveView() {
        return "backend/auth/menu/add";
    }

    @GetMapping("update")
    public String editView() {
        return "backend/auth/menu/edit";
    }
}
