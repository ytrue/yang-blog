package com.yang.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.blog.entity.Permission;
import com.yang.blog.util.QueryCondition;
import com.yang.blog.util.ResponseData;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

public interface IPermissionService extends IService<Permission> {

    /**
     * 查询所有
     *
     * @return
     */
    Map<String, Object> all();

    /**
     * 判断是否存在
     *
     * @param url
     * @param id
     * @return
     */
    Boolean exist(String url, Integer id);

    /**
     * 添加
     *
     * @param admin
     * @param bindingResult
     * @return
     */
    ResponseData<Object> add(Permission permission, BindingResult bindingResult);

    /**
     * 修改
     *
     * @param admin
     * @param bindingResult
     * @return
     */
    ResponseData<Object> update1(Permission permission, BindingResult bindingResult);

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
