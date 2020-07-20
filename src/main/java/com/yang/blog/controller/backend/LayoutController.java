package com.yang.blog.controller.backend;

import com.yang.blog.entity.Permission;
import com.yang.blog.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：首页布局控制器
 */
@Controller
public class LayoutController {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 布局首页
     *
     * @return
     */
    @RequestMapping(value = {"/admin/index", "/admin/", "/admin"})
    public String index(Model model) {

        //获得登录用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        List<Permission> permissions = adminMapper.permissionFindByAdmin(username);
        //获得该角色有的角色，在之后获得权限
        /*List<Permission> permissionList = permissionService.list(
                new QueryWrapper<>()
        );*/
        List<Permission> menu = getChildManyGroup(permissions, 0);
        model.addAttribute("username", username);
        model.addAttribute("menu", menu);
        return "backend/layout";
    }


    /**
     * 根据id返回所有子集分类,(多维List集合,List中含有子集List)
     * <p>
     * 1
     * 11
     * 111
     * 2
     * 22
     * 222
     *
     * @param list
     * @param pid
     * @return
     */
    private List<Permission> getChildManyGroup(List<Permission> list, int pid) {

        List<Permission> arr = new ArrayList<>();
        for (Permission permission : list) {
            if (pid == permission.getPid()) {
                permission.setChildrenList(getChildManyGroup(list, permission.getId()));
                arr.add(permission);
            }
        }
        return arr;
    }

}
