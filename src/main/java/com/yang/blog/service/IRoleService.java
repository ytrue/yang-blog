package com.yang.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.blog.entity.Role;
import com.yang.blog.util.QueryCondition;
import com.yang.blog.util.ResponseData;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

public interface IRoleService extends IService<Role> {
    /**
     * 分页
     *
     * @param params
     * @return
     */
    Object queryPage(QueryCondition params);

    /**
     * 判断是否存在
     *
     * @param code
     * @param id
     * @return
     */
    Boolean exist(String code, Integer id);

    /**
     * 添加
     *
     * @param role
     * @param bindingResult
     * @return
     */
    ResponseData<Object> add(Role role, BindingResult bindingResult);

    /**
     * 修改
     *
     * @param role
     * @param bindingResult
     * @return
     */
    ResponseData<Object> update1(Role role, BindingResult bindingResult);

    /**
     * id查找
     *
     * @param id
     * @return
     */
    ResponseData<Map<String, Object>> find(Long id);

    ResponseData<Object> del(List<Long> ids);
}
