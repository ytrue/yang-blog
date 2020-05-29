package com.yang.blog.security.url;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.blog.entity.Permission;
import com.yang.blog.entity.Role;
import com.yang.blog.entity.RoleMenu;
import com.yang.blog.mapper.PermissionMapper;
import com.yang.blog.mapper.RoleMapper;
import com.yang.blog.mapper.RolePermissionMapper;
import com.yang.blog.security.UrlAuthConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author：yangyi
 * @date：2020/5/16 18:45
 * @description： 自定义UrlFilterInvocationSecurityMetadataSource
 * 实现FilterInvocationSecurityMetadataSource重写getAttributes()方法
 * 获取访问该url所需要的角色权限信息
 */
@Component
@Slf4j
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {


    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Autowired
    RoleMapper roleMapper;

    /***
     * 返回该url所需要的用户权限信息
     *
     * @param object: 储存请求url信息
     * @return: null：标识不需要任何权限都可以访问
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取当前请求url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();

        // TODO 忽略url请放在此处进行过滤放行
        if ("/login".equals(requestUrl) || requestUrl.contains("logout")) {
            return null;
        }


        log.info("UrlFilterInvocationSecurityMetadataSource 触发，请求url："+requestUrl);

        if (UrlAuthConfig.permitAll().contains(requestUrl)) {
            return null;
        }

        // 数据库中所有url
        List<Permission> permissionList = permissionMapper.selectList(null);

        for (Permission permission : permissionList) {
            // 获取该url所对应的权限
            if (requestUrl.equals(permission.getUrl())) {

                QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("permission_id", permission.getId());
                List<RoleMenu> permissions = rolePermissionMapper.selectList(queryWrapper);

                List<String> roles = new LinkedList<>();
                if (!CollectionUtils.isEmpty(permissions)) {
                    Integer roleId = permissions.get(0).getRoleId();
                    Role role = roleMapper.selectById(roleId);
                    roles.add(role.getCode());
                }
                // 保存该url对应角色权限信息
                return SecurityConfig.createList(roles.toArray(new String[roles.size()]));
            }
        }
        //
        return SecurityConfig.createList("role_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
