package com.yang.blog.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.blog.entity.Admin;
import com.yang.blog.mapper.AdminMapper;
import com.yang.blog.service.IAdminService;
import com.yang.blog.util.PageVo;
import com.yang.blog.util.Query;
import com.yang.blog.util.QueryCondition;
import com.yang.blog.util.ResponseData;
import com.yang.blog.validate.VerificationJudgement;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yang
 * @since 2020-05-16
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Override
    public PageVo queryPage(QueryCondition params) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        IPage<Admin> page = this.page(
                new Query<Admin>().getPage(params),
                queryWrapper.select(
                        "id",
                        "username",
                        "createtime"
                )
        );
        return new PageVo(page);
    }

    @Override
    public ResponseData<Object> add(Admin admin, BindingResult bindingResult) {
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
    public ResponseData<Object> update1(Admin admin, BindingResult bindingResult) {
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
    public ResponseData<Admin> find(Long id) {
        Admin admin = getOne(new QueryWrapper<Admin>().
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
        QueryWrapper<Admin> query = new QueryWrapper<>();
        query.eq("username", username);
        query.ne("id", id);
        Admin admin = getOne(query);
        return admin == null;
    }


}
