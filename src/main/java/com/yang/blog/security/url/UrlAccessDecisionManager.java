package com.yang.blog.security.url;

import com.yang.blog.security.UrlAuthConfig;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * <p> 对访问url进行权限认证处理 </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2019/10/15 14:21
 */
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {

    /**
     * @param authentication: 当前登录用户的角色信息
     * @param object:         请求url信息
     * @param collection:     `UrlFilterInvocationSecurityMetadataSource`中的getAttributes方法传来的，表示当前请求需要的角色（可能有多个）
     * @return: void
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection) throws AccessDeniedException, AuthenticationException {


        // 遍历角色
        for (ConfigAttribute ca : collection) {
            // ① 当前url请求需要的权限
            String needRole = ca.getAttribute();

            System.out.println("needRole----------" + needRole);


            if ("role_login".equals(needRole)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new BadCredentialsException("未登录!");
                } else {
                    // 获取当前请求url
                    String requestUrl = ((FilterInvocation) object).getRequestUrl();
                    //这里再判断一次
                    if (UrlAuthConfig.authenticated().contains(requestUrl)) {
                        return;
                    } else {
                        throw new AccessDeniedException("未授权该url！");
                    }
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
