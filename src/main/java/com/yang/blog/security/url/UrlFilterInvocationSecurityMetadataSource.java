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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
        String requestUrl = "";
        String fullRequestUrl = ((FilterInvocation) object).getFullRequestUrl();
        try {
            URL url = new URL(fullRequestUrl);
            requestUrl = url.getPath();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // TODO 忽略url请放在此处进行过滤放行
        if ("/admin/login".equals(requestUrl) || requestUrl.contains("/admin/logout")) {
            return null;
        }


        log.info("UrlFilterInvocationSecurityMetadataSource 触发，请求url：" + requestUrl);

        if (UrlAuthConfig.permitAll().contains(requestUrl)) {
            return null;
        }

        // 数据库中所有url
        List<Permission> permissionList = permissionMapper.selectList(null);

        HashSet<String> roles = new HashSet<>();
        for (Permission permission : permissionList) {
            // 获取该url所对应的权限
            if (requestUrl.equals(permission.getUrl())) {

                QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("permission_id", permission.getId());
                List<RoleMenu> permissions = rolePermissionMapper.selectList(queryWrapper);
                //通过role id获得role code
                if (!CollectionUtils.isEmpty(permissions)) {
                    List<Integer> roleIds = permissions.stream().map(RoleMenu::getRoleId).collect(Collectors.toList());
                    List<Role> roleList = roleMapper.selectList(new QueryWrapper<Role>().in("id", roleIds));
                    System.out.println(roleList);
                    roleList.forEach(role -> roles.add(role.getCode()));
                }
            }
        }
        // 保存该url对应角色权限信息
        return !roles.isEmpty() ? SecurityConfig.createList(roles.toArray(new String[0]))
                : SecurityConfig.createList("role_login");
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
