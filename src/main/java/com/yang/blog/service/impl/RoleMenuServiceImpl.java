package com.yang.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.blog.entity.RoleMenu;
import com.yang.blog.mapper.RolePermissionMapper;
import com.yang.blog.service.IRoleMenuService;
import org.springframework.stereotype.Service;

@Service
public class RoleMenuServiceImpl extends ServiceImpl<RolePermissionMapper, RoleMenu> implements IRoleMenuService {
}
