package com.yang.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.blog.entity.Admin;

import java.util.List;
import java.util.Map;


/**
 * <p> 系统管理-用户基础信息表 Mapper 接口 </p>
 *
 * @author: zhengqing
 * @date: 2019-08-19
 */
public interface AdminMapper extends BaseMapper<Admin> {

    List<Map<String, Object>> roleFindByIdAdmin(Long id);
}
