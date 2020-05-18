package com.yang.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.blog.entity.Admin;
import com.yang.blog.entity.Role;
import com.yang.blog.entity.AdminRole;
import com.yang.blog.mapper.RoleMapper;
import com.yang.blog.mapper.AdminMapper;
import com.yang.blog.mapper.AdminRoleMapper;
import com.yang.blog.security.dto.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author：yangyi
 * @date：2020/5/16 18:33
 * @description：
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminRoleMapper userRoleMapper;

    /***
     * 根据账号获取用户信息
     * @param username:
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<Admin> userList = userMapper.selectList(queryWrapper);
        Admin user;

        // 判断用户是否存在
        if (!CollectionUtils.isEmpty(userList)) {
            user = userList.get(0);
        } else {
            throw new UsernameNotFoundException("用户名不存在！");
        }

        // 返回UserDetails实现类
        return new SecurityUser(user, getUserRoles(user.getId()));
    }

    /***
     * 根据token获取用户权限与基本信息
     *
     * @param token:
     * @return: com.zhengqing.config.security.dto.SecurityUser
     */
    public SecurityUser getUserByToken(String token) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("token", token);

        Admin user = null;
        List<Admin> loginList = userMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(loginList)) {
            user = loginList.get(0);
        }
        return user != null ? new SecurityUser(user, getUserRoles(user.getId())) : null;
    }

    /**
     * 根据用户id获取角色权限信息
     *
     * @param userId
     * @return
     */
    private List<Role> getUserRoles(Integer userId) {
        QueryWrapper<AdminRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<AdminRole> userRoles = userRoleMapper.selectList(queryWrapper);

        List<Role> roleList = new LinkedList<>();

        for (AdminRole userRole : userRoles) {
            Role role = roleMapper.selectById(userRole.getRoleId());
            roleList.add(role);
        }
        return roleList;
    }
}
