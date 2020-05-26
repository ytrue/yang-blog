package com.yang.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.blog.entity.Admin;
import com.yang.blog.util.QueryCondition;
import com.yang.blog.util.ResponseData;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

public interface IAdminService extends IService<Admin> {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    Map<String, Object> queryPage(QueryCondition params);

    /**
     * 判断是否存在
     *
     * @param username
     * @param id
     * @return
     */
    Boolean exist(String username, Integer id);

    /**
     * 添加
     *
     * @param admin
     * @param bindingResult
     * @return
     */
    ResponseData<Object> add(Admin admin, BindingResult bindingResult);

    /**
     * 修改
     *
     * @param admin
     * @param bindingResult
     * @return
     */
    ResponseData<Object> update1(Admin admin, BindingResult bindingResult);

    /**
     * id查找
     *
     * @param id
     * @return
     */
    ResponseData<Map<String, Object>> find(Long id);

    /**
     * id查找角色和全部角色
     *
     * @param id
     * @return
     */
    ResponseData<Object> myRole(Integer id);


    /**
     * 删除
     *
     * @param ids
     * @return
     */
    ResponseData<Object> del(List<Long> ids);
}
