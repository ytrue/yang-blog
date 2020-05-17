package com.yang.blog.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.blog.entity.Admin;
import com.yang.blog.util.PageVo;
import com.yang.blog.util.QueryCondition;
import com.yang.blog.util.ResponseData;
import org.springframework.validation.BindingResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yang
 * @since 2020-05-16
 */
public interface IAdminService extends IService<Admin> {

    PageVo queryPage(QueryCondition params);


    Boolean exist(String username, Integer id);

    ResponseData<Object> add(Admin admin, BindingResult bindingResult);

    ResponseData<Object> update1(Admin admin, BindingResult bindingResult);

    ResponseData<Admin> find(Long id);
}
