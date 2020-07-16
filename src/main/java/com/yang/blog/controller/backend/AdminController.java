package com.yang.blog.controller.backend;


import com.yang.blog.dto.QueryParam;
import com.yang.blog.entity.Admin;
import com.yang.blog.service.IAdminService;
import com.yang.blog.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：文章栏目实体类
 */
@RequestMapping("admin/auth/admin")
@Controller
public class AdminController {

    @Autowired
    private IAdminService adminService;

    /**
     * 排序分页查询
     *
     * @param queryParam
     * @return
     */
    @ResponseBody
    @PostMapping("index")
    public Map<String, Object> index(@RequestBody QueryParam queryParam) {
        System.out.println(queryParam);
        return adminService.queryPage(queryParam);
    }

    /**
     * 根据id获得角色
     */
    @ResponseBody
    @PostMapping("assign")
    public ResponseData<Object> myRole(@RequestParam Long id) {
        return adminService.myRole(id);
    }


    /**
     * 添加数据
     *
     * @param admin
     * @return
     */
    @ResponseBody
    @PostMapping("add")
    public ResponseData<Object> save(
            @RequestBody
            @Valid Admin admin,
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
    @GetMapping(value = "find/{id}")
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
    @PutMapping("update")
    public ResponseData<Object> update(
            @Validated
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
    @DeleteMapping("delete")
    public ResponseData<Object> delete(@RequestBody List<Long> ids) {
        return adminService.del(ids);
    }

    /**
     * 分配角色
     *
     * @param roleId
     * @param id
     * @return
     */
    @ResponseBody
    @PutMapping("assign")
    public ResponseData<Object> assignRole(
            @RequestParam(value = "roleId") String roleId,
            @RequestParam(value = "id") Long id
    ) {
        return adminService.assignRole(roleId, id);
    }

    @GetMapping("index")
    public String indexView() {
        return "backend/auth/admin/index";
    }

    @GetMapping("add")
    public String saveView() {
        return "backend/auth/admin/add";
    }

    @GetMapping("update")
    public String editView() {
        return "backend/auth/admin/edit";
    }

    @GetMapping("assign")
    public String assignView() {
        return "backend/auth/admin/assign";
    }

}
