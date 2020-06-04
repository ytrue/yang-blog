package com.yang.blog.security.url;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.blog.entity.Permission;
import com.yang.blog.service.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p> 对访问url进行权限认证处理 </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2019/10/15 14:21
 */
@Component
@Slf4j
public class UrlAccessDecisionManager implements AccessDecisionManager {

    @Autowired
    private IPermissionService permissionService;

    /**
     * @param authentication: 当前登录用户的角色信息
     * @param object:         请求url信息
     * @param collection:     `UrlFilterInvocationSecurityMetadataSource`中的getAttributes方法传来的，表示当前请求需要的角色（可能有多个）
     * @return: void
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection) throws AccessDeniedException, AuthenticationException {

        log.info("UrlAccessDecisionManager 触发！！！");

        // 获取当前请求url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();

        //在这里查询，节省查询次数
        List<Permission> permissionList = permissionService.list(new QueryWrapper<Permission>().select("url"));
        List<String> allUrl = permissionList.stream().map(Permission::getUrl).collect(Collectors.toList());

        // 遍历角色
        for (ConfigAttribute ca : collection) {
            // ① 当前url请求需要的权限
            String needRole = ca.getAttribute();
            if ("role_login".equals(needRole)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new BadCredentialsException("未登录!");
                }
                //这里去获得所有的权限，，和当前url对比一下，如果当前url对比没有就放，有的话就下一步
                if (!allUrl.contains(requestUrl)){
                    return;
                }
            }

            // ② 当前用户所具有的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            for (GrantedAuthority authority : authorities) {
                // 只要包含其中一个角色即可访问
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("请联系管理员分配权限！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
