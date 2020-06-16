package com.yang.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.blog.entity.Admin;

import java.util.List;
import java.util.Map;


/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：管理员Mapper接口
 */
public interface AdminMapper extends BaseMapper<Admin> {

    List<Map<String, Object>> roleFindByIdAdmin(Long id);
}
