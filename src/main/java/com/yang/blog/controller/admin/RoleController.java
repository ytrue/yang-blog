package com.yang.blog.controller.admin;

import com.yang.blog.entity.Admin;
import com.yang.blog.entity.Role;
import com.yang.blog.service.IRoleService;
import com.yang.blog.util.QueryCondition;
import com.yang.blog.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class RoleController {

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
    public ResponseData<Object> list(QueryCondition queryCondition) {
        Object page = roleService.queryPage(queryCondition);
        return ResponseData.success(page);

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
            @Validated Role role,
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
}
