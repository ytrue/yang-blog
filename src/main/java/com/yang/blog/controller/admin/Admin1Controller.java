package com.yang.blog.controller.admin;

import com.yang.blog.entity.Admin1;
import com.yang.blog.service.IAdmin1Service;
import com.yang.blog.util.QueryCondition;
import com.yang.blog.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author：yangyi
 * @date：2020/5/16 10:07
 * @description：管理员控制器
 */
@Controller
public class Admin1Controller {
    @Autowired
    public IAdmin1Service adminService;


    /**
     * 排序分页查询
     *
     * @param queryCondition
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/auth/admin/list")
    public Object list(QueryCondition queryCondition) {

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
            @Validated({Admin1.AdminAdd.class}) Admin1 admin,
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
    public ResponseData<Admin1> find(@PathVariable("id") Long id) {
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
            @Validated(Admin1.AdminEdit.class)
            @RequestBody Admin1 admin,
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
        return adminService.removeByIds(ids) ? ResponseData.success("数据删除成功！") : ResponseData.fail("数据删除失败！");
    }

}
