package com.yang.blog.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.blog.entity.Admin1;
import com.yang.blog.util.QueryCondition;
import com.yang.blog.util.ResponseData;
import org.springframework.validation.BindingResult;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yang
 * @since 2020-05-16
 */
public interface IAdmin1Service extends IService<Admin1> {

    Page<Map<String, Object>> queryPage(QueryCondition params);


    Boolean exist(String username, Integer id);

    ResponseData<Object> add(Admin1 admin, BindingResult bindingResult);

    ResponseData<Object> update1(Admin1 admin, BindingResult bindingResult);

    ResponseData<Admin1> find(Long id);
}
