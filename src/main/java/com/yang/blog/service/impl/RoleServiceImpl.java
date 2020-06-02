package com.yang.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.blog.entity.*;
import com.yang.blog.mapper.RoleMapper;
import com.yang.blog.service.*;
import com.yang.blog.util.QueryCondition;
import com.yang.blog.util.ResponseData;
import com.yang.blog.validate.VerificationJudgement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IAdminRoleService adminRoleService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    public ResponseData<Object> assign(String menuId, Long id) {
        List<Long> menuIdList = JSON.parseObject(menuId, new TypeReference<List<Long>>() {
        });

        return null;
    }

    /**
     * 获得my menu列表
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> myMenu() {
        //获得全部
        List<Map<String, Object>> menuAll = permissionService.listMaps(
                new QueryWrapper<Permission>()
                        .select("id", "pid", "name")
        );

        //通过role id获得该有的
        List<Map<String, Object>> menuIdMap =
                roleMenuService.listMaps(
                        new QueryWrapper<RoleMenu>()
                                .eq("role_id", 1)
                                .select("permission_id")
                );

        ArrayList<Long> menuIds = new ArrayList<>();
        List<Map<String, Object>> myMenu = new ArrayList<>();

        if (!menuIdMap.isEmpty()) {
            menuIdMap.forEach(map -> menuIds.add(Long.valueOf(String.valueOf(map.get("permission_id")))));
            myMenu = permissionService.listMaps(
                    new QueryWrapper<Permission>()
                            .in("id", menuIds)
                            .select("id", "pid", "name")
            );
        }
        //获得差集
        menuAll.removeAll(myMenu);
        //循环
        menuAll.forEach(map -> {
            map.put("open", true);
            map.put("checked", false);
        });
        //循环
        myMenu.forEach(map -> {
            map.put("checked", true);
        });
        menuAll.addAll(myMenu);
        return menuAll;
    }

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> queryPage(QueryCondition params) {
        List<Map<String, Object>> rows = listMaps(
                new QueryWrapper<Role>()
                        .select("id", "code", "name", "create_time")
                        .orderByDesc("id")
                        .last(params.getPageSql())
        );

        return ResponseData.list(count(), rows);
    }

    /**
     * 获得所有
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> all() {
        return listMaps(
                new QueryWrapper<Role>()
                        .select("id", "name", "code")
                        .orderByDesc("id")
        );
    }

    /**
     * 判断是否存在
     *
     * @param code
     * @param id
     * @return
     */
    @Override
    public Boolean exist(String code, Integer id) {
        QueryWrapper<Role> query = new QueryWrapper<>();
        query.eq("code", code);
        query.ne("id", id);
        return count(query) == 0;
    }

    /**
     * 添加数据
     *
     * @param role
     * @param bindingResult
     * @return
     */
    @Override
    public ResponseData<Object> add(Role role, BindingResult bindingResult) {
        ArrayList<String> errorList = VerificationJudgement.hasErrror(bindingResult);
        if (!errorList.isEmpty()) {
            return ResponseData.fail(2, "error", errorList);
        }
        try {
            save(role);
            return ResponseData.success(null);
        } catch (Exception e) {
            return ResponseData.fail(e.getMessage());
        }
    }

    /**
     * 修改数据
     *
     * @param role
     * @param bindingResult
     * @return
     */
    @Override
    public ResponseData<Object> update1(Role role, BindingResult bindingResult) {
        ArrayList<String> errorList = VerificationJudgement.hasErrror(bindingResult);
        if (!errorList.isEmpty()) {
            return ResponseData.fail(2, "error", errorList);
        }
        //判断否存在
        if (!exist(role.getCode(), role.getId())) {
            return ResponseData.fail(2, "error", Collections.singletonList("此角色编码已存在！"));
        }
        try {
            updateById(role);
            return ResponseData.success(null);
        } catch (Exception e) {
            return ResponseData.fail("数据修改失败！");
        }
    }

    /**
     * id查找
     *
     * @param id
     * @return
     */
    @Override
    public ResponseData<Map<String, Object>> find(Long id) {
        Map<String, Object> map = getMap(
                new QueryWrapper<Role>().
                        eq("id", id).
                        select(
                                "id",
                                "name",
                                "code",
                                "remarks"
                        )
        );
        return ResponseData.success(map);
    }

    @Override
    public ResponseData<Object> del(List<Long> ids) {
        try {
            /*//这里要判断一下的，判断是否有管理员在使用
            int count = adminRoleService.count(new QueryWrapper<AdminRole>()
                    .in("role_id", ids));

            //那就是有数据
            if (count > 0) {
                return ResponseData.fail("有角色关联！");
            }*/
            removeByIds(ids);
            return ResponseData.success("数据删除成功！");
        } catch (Exception e) {
            return ResponseData.fail(e.getMessage());
        }
    }
}
