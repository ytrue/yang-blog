package com.yang.blog.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.blog.entity.Admin1;
import com.yang.blog.mapper.Admin1Mapper;
import com.yang.blog.service.IAdmin1Service;
import com.yang.blog.util.QueryCondition;
import com.yang.blog.util.ResponseData;
import com.yang.blog.validate.VerificationJudgement;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Map;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yang
 * @since 2020-05-16
 */
@Service
public class Admin1ServiceImpl extends ServiceImpl<Admin1Mapper, Admin1> implements IAdmin1Service {

    @Override
    public Page<Map<String, Object>> queryPage(QueryCondition params) {
        QueryWrapper<Admin1> queryWrapper = new QueryWrapper<>();
        Page<Map<String, Object>> mapPage = pageMaps(
                new Page<>(1, 5),
                queryWrapper.select(
                        "id",
                        "username",
                        "createtime",
                        "enabled"
                )
        );

        return mapPage;
    }

    @Override
    public ResponseData<Object> add(Admin1 admin, BindingResult bindingResult) {
        ArrayList<String> errorList = VerificationJudgement.hasErrror(bindingResult);
        if (!errorList.isEmpty()) {
            return ResponseData.fail(2, "error", errorList);
        }
        try {
            admin.setCreatetime(System.currentTimeMillis());
            //密码加密
            admin.setPassword(admin.getUsername());

            save(admin);
            return ResponseData.success(null);
        } catch (Exception e) {
            return ResponseData.fail(e.getMessage());
        }
    }

    @Override
    public ResponseData<Object> update1(Admin1 admin, BindingResult bindingResult) {
        ArrayList<String> errorList = VerificationJudgement.hasErrror(bindingResult);
        if (!errorList.isEmpty()) {
            return ResponseData.fail(2, "error", errorList);
        }

        //判断账户是否存在
        if (!exist(admin.getUsername(), admin.getId())) {
            return ResponseData.fail(2, "error", errorList);
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
     * id查询
     *
     * @param id
     * @return
     */
    @Override
    public ResponseData<Admin1> find(Long id) {
        Admin1 admin = getOne(new QueryWrapper<Admin1>().
                eq("id", id).
                select(
                        "id",
                        "username"
                )
        );
        return ResponseData.success(admin);
    }

    /**
     * 查询指定数据
     *
     * @param username
     * @param id
     * @return
     */
    @Override
    public Boolean exist(String username, Integer id) {
        QueryWrapper<Admin1> query = new QueryWrapper<>();
        query.eq("username", username);
        query.ne("id", id);
        Admin1 admin = getOne(query);
        return admin == null;
    }


}
