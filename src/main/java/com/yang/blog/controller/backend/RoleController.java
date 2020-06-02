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
@RequestMapping("admin/auth/role/")
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
    @PostMapping("index")
    public Map<String, Object> index(QueryCondition queryCondition) {
        return roleService.queryPage(queryCondition);
    }


    /**
     * 添加数据
     *
     * @param role
     * @param bindingResult
     * @return
     */
    @ResponseBody
    @PostMapping("add")
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
    @GetMapping("find/{id}")
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
    @PutMapping("update")
    public ResponseData<Object> update(
            @Validated
            @RequestBody Role role,
            BindingResult bindingResult
    ) {
        return roleService.update1(role, bindingResult);
    }

    /**
     * 分配权限
     *
     * @return
     */
    @ResponseBody
    @PostMapping("assign")
    public ResponseData<Object> assign(
            @RequestParam(value = "menuId") String menuId,
            @RequestParam(value = "id") Long id
    ) {
        //获得数组的集合，想删除一样,获得自身的id
        return roleService.assign(menuId, id);
    }


    //获得menu
    @ResponseBody
    @PostMapping("menu")
    public List<Map<String, Object>> myMenu() {
        return roleService.myMenu();
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
        return roleService.del(ids);
    }


    @GetMapping("index")
    public String indexView() {
        return "backend/auth/role/index";
    }

    @GetMapping("add")
    public String saveView() {
        return "backend/auth/role/add";
    }

    @GetMapping("update")
    public String editView() {
        return "backend/auth/role/edit";
    }

    @GetMapping("details")
    public String findView() {
        return "backend/auth/role/find";
    }

    @GetMapping("assign")
    public String assignView() {
        return "backend/auth/role/assign";
    }

}
