package com.yang.blog.controller.backend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.blog.entity.Permission;
import com.yang.blog.service.IPermissionService;
import com.yang.blog.util.ResponseData;
import com.yang.blog.validate.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/auth/menu")
public class MenuController extends BasicController {

    @Autowired
    private IPermissionService permissionService;

    /**
     * 获得所有的权限
     */
    @ResponseBody
    @PostMapping(value = "all")
    public Map<String, Object> all() {
        return permissionService.all();
    }

    @ResponseBody
    @PostMapping(value = "test")
    public Object test() {
        //获得全部
        List<Map<String, Object>> maps = permissionService.listMaps(
                new QueryWrapper<Permission>()
                        .select("id", "pid", "name")
        );


        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("id", i);
            hashMap.put("name", "yang" + i);
            list.add(hashMap);
        }

        ArrayList<Map<String, Object>> list1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> hashMap1 = new HashMap<>();
            hashMap1.put("id", i);
            hashMap1.put("name", "yang" + 1);
            list1.add(hashMap1);
        }

        list.removeAll(list1);

        System.out.println(list);


        return null;
        //return permissionService.list();
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
