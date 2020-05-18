package com.yang.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public Object queryPage(QueryCondition params) {
//        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
//        IPage<Admin> page = this.page(
//                new Query<Admin>().getPage(params),
//                queryWrapper.select(
//                        "id",
//                        "username",
//                        "create_time"
//                )
//        );
//        return new PageVo(page);

        Page<Map<String, Object>> page = pageMaps(
                new Page<>(1, 5),
                new QueryWrapper<Admin>().
                        select(
                                "id",
                                "username",
                                "create_time"
                        )
        );
        return page;
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
        Admin admin = getOne(query);
        return admin == null;
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

    @Override
    public ResponseData<Object> del(List<Long> ids) {
        try {
            removeByIds(ids);
            return ResponseData.success("数据删除成功！");
        } catch (Exception e) {
            return ResponseData.fail(e.getMessage());
        }
    }
}
