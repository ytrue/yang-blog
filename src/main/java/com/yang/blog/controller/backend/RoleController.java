package com.yang.blog.controller.backend;

import com.yang.blog.entity.Role;
import com.yang.blog.service.IRoleService;
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
@RequestMapping("/admin")
public class RoleController extends BasicController {

    @Autowired
    private IRoleService roleService;

    /**
     * 排序分页查询
     *
     * @param queryCondition
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/auth/role/list")
    public Map<String, Object> list(QueryCondition queryCondition) {
        return roleService.queryPage(queryCondition);
    }

    /**
     * 获得所有的角色
     */
    @ResponseBody
    @GetMapping(value = "/auth/role/all")
    public List<Map<String, Object>> all() {
        return roleService.all();
    }

    /**
     * 添加数据
     *
     * @param role
     * @return
     */
    @ResponseBody
    @PostMapping("/auth/role/add")
    public ResponseData<Object> save(
            @RequestBody
            @Validated({Scene.Add.class}) Role role,
            BindingResult bindingResult
    ) {
        return roleService.add(role, bindingResult);
    }

    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/auth/role/find/{id}")
    public ResponseData<Map<String, Object>> find(@PathVariable("id") Long id) {
        return roleService.find(id);
    }


    /**
     * 修改数据
     *
     * @param role
     * @return
     */
    @ResponseBody
    @PutMapping("/auth/role/update")
    public ResponseData<Object> update(
            @Validated
            @RequestBody Role role,
            BindingResult bindingResult
    ) {
        return roleService.update1(role, bindingResult);
    }


    /**
     * id 删除
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @DeleteMapping("/auth/role/delete")
    public ResponseData<Object> delete(@RequestBody List<Long> ids) {
        return roleService.del(ids);
    }


    @GetMapping("/auth/role/index")
    public String indexView() {
        return "backend/auth/role/index";
    }

    @GetMapping("/auth/role/save")
    public String saveView() {
        return "backend/auth/role/save";
    }

    @GetMapping("/auth/role/edit")
    public String editView() {
        return "backend/auth/role/edit";
    }

    @GetMapping("/auth/role/details")
    public String findView() {
        return "backend/auth/role/find";
    }
}
