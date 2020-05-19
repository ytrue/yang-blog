package com.yang.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.blog.entity.Admin;
import com.yang.blog.entity.AdminRole;
import com.yang.blog.entity.Role;
import com.yang.blog.mapper.RoleMapper;
import com.yang.blog.service.IAdminRoleService;
import com.yang.blog.service.IAdminService;
import com.yang.blog.service.IRoleService;
import com.yang.blog.util.QueryCondition;
import com.yang.blog.util.ResponseData;
import com.yang.blog.validate.VerificationJudgement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IAdminRoleService adminRoleService;

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public Object queryPage(QueryCondition params) {
        Page<Map<String, Object>> page = pageMaps(
                new Page<>(1, 5),
                new QueryWrapper<Role>().
                        select(
                                "id",
                                "code",
                                "name",
                                "create_time"
                        )
        );
        return page;
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
        Role role = getOne(query);
        return role == null;
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
        if (!exist(role.getName(), role.getId())) {
            return ResponseData.fail(2, "error", errorList);
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
