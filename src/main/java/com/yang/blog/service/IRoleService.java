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
     * 分配权限
     *
     * @return
     */
    ResponseData<Object> assign(String menuId, Long id);

    /**
     * 获得my权限列表
     *
     * @return
     * @param id
     */
    List<Map<String, Object>> myMenu(Long id);

    /**
     * 分页
     *
     * @param params
     * @return
     */
    Map<String, Object> queryPage(QueryCondition params);

    /**
     * 获得所有数据
     *
     * @return
     */
    List<Map<String, Object>> all();

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

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    ResponseData<Object> del(List<Long> ids);
}
