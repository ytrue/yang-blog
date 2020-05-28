package com.yang.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.internal.$Gson$Preconditions;
import com.yang.blog.entity.Permission;
import com.yang.blog.mapper.PermissionMapper;
import com.yang.blog.service.IPermissionService;
import com.yang.blog.util.ResponseData;
import com.yang.blog.validate.VerificationJudgement;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public Map<String, Object> all() {
        //tree处理

        int yang = 10 / 0;
        List<Permission> children = getChildren(list(), 0, 0);
        return ResponseData.list(0, children);
    }

    /**
     * tree
     *
     * @param list
     * @param pid
     * @return
     */
    private List<Permission> getChildren(List<Permission> list, int pid, int level) {
        List<Permission> arr = new ArrayList<>();
        for (Permission permission : list) {
            if (pid == permission.getPid()) {
                permission.setLevel(level);
                StringBuilder str = new StringBuilder();
                if (level > 0) {
                    for (int z = 0; z < level; z++) {
                        str.append("---");
                    }
                }
                permission.setName(str + permission.getName());
                arr.add(permission);
                if (level < 1) {
                    arr.addAll(getChildren(list, permission.getId(), level + 1));
                }

            }
        }
        return arr;
    }


    /**
     * 判断是否存在在
     *
     * @param url
     * @param id
     * @return
     */
    @Override
    public Boolean exist(String url, Integer id) {
        return null;
    }

    /**
     * 新增
     *
     * @param permission
     * @param bindingResult
     * @return
     */
    @Override
    public ResponseData<Object> add(Permission permission, BindingResult bindingResult) {
        ArrayList<String> errorList = VerificationJudgement.hasErrror(bindingResult);
        if (!errorList.isEmpty()) {
            return ResponseData.fail(2, "error", errorList);
        }
        try {
            save(permission);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.fail(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param permission
     * @param bindingResult
     * @return
     */
    @Override
    public ResponseData<Object> update1(Permission permission, BindingResult bindingResult) {
        ArrayList<String> errorList = VerificationJudgement.hasErrror(bindingResult);
        if (!errorList.isEmpty()) {
            return ResponseData.fail(2, "error", errorList);
        }
        //判断账户是否存在
        if (!exist(permission.getUrl(), permission.getId())) {
            return ResponseData.fail(2, "error", Collections.singletonList("此账户已存在！"));
        }
        try {
            updateById(permission);
            return ResponseData.success();
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
        Map<String, Object> map = getMap(new QueryWrapper<>());
        return ResponseData.success(map);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @Override
    public ResponseData<Object> del(List<Long> ids) {
        try {
            removeByIds(ids);
            return ResponseData.success();
        } catch (Exception e) {
            return ResponseData.fail(e.getMessage());
        }
    }
}
