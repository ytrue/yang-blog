package com.yang.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.blog.entity.Admin;
import com.yang.blog.mapper.AdminMapper;
import com.yang.blog.service.IAdminService;
import com.yang.blog.service.IRoleService;
import com.yang.blog.util.QueryCondition;
import com.yang.blog.util.ResponseData;
import com.yang.blog.validate.VerificationJudgement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private IRoleService roleService;

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> queryPage(QueryCondition params) {

        List<Map<String, Object>> rows = listMaps(
                new QueryWrapper<Admin>()
                        .select("id", "username", "nick_name", "create_time")
                        .orderByDesc("id")
                        .last(params.getPageSql())
        );

        return ResponseData.list(count(), rows);
    }

    /**
     * 判断是否存在
     *
     * @param username
     * @param id
     * @return
     */
    @Override
    public Boolean exist(String username, Integer id) {
        QueryWrapper<Admin> query = new QueryWrapper<>();
        query.eq("username", username);
        query.ne("id", id);
        return count(query) == 0;
    }

    /**
     * 添加数据
     *
     * @param admin
     * @param bindingResult
     * @return
     */
    @Override
    public ResponseData<Object> add(Admin admin, BindingResult bindingResult) {
        ArrayList<String> errorList = VerificationJudgement.hasErrror(bindingResult);
        if (!errorList.isEmpty()) {
            return ResponseData.fail(2, "error", errorList);
        }
        try {
            //admin.setCreateTime(System.currentTimeMillis());
            //密码加密
            admin.setPassword(admin.getPassword());
            admin.setSalt("123");

            save(admin);
            return ResponseData.success(null);
        } catch (Exception e) {
            return ResponseData.fail(e.getMessage());
        }
    }

    /**
     * 修改数据
     *
     * @param admin
     * @param bindingResult
     * @return
     */
    @Override
    public ResponseData<Object> update1(Admin admin, BindingResult bindingResult) {
        ArrayList<String> errorList = VerificationJudgement.hasErrror(bindingResult);
        if (!errorList.isEmpty()) {
            return ResponseData.fail(2, "error", errorList);
        }
        //判断账户是否存在
        if (!exist(admin.getUsername(), admin.getId())) {
            return ResponseData.fail(2, "error", Collections.singletonList("此账户已存在！"));
        }

        String userPwd = admin.getUsername();
        if (userPwd == null || userPwd.isEmpty()) {
            admin.setPassword(null);
        } else {
            //这里要加密的
            admin.setPassword(admin.getUsername());
        }
        try {
            updateById(admin);
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
                new QueryWrapper<Admin>().
                        eq("id", id).
                        select(
                                "id",
                                "username",
                                "nick_name",
                                "create_time"
                        )
        );
        return ResponseData.success(map);
    }

    /**
     * id查找角色和全部角色
     *
     * @param id
     * @return
     */
    @Override
    public ResponseData<Object> myRole(Integer id) {
        //获得所有的role
        List<Map<String, Object>> allRole = roleService.all();
        //根据id获得自己的角色
        List<Map<String, Object>> myRole = adminMapper.roleFindByIdAdmin(id);

        HashMap<String, List<Map<String, Object>>> rstMap = new HashMap<>();
        rstMap.put("my_role", myRole);
        rstMap.put("all_role", allRole);
        return ResponseData.success(rstMap);
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
