package com.yang.blog.security.filter;

import com.yang.blog.security.dto.SecurityUser;
import com.yang.blog.service.impl.UserDetailsServiceImpl;
import com.yang.blog.util.MultiReadHttpServletRequest;
import com.yang.blog.util.MultiReadHttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 实现访问鉴权
 *
 * @author：yangyi
 * @date：2020/5/16 18:08
 * @description： 每次访问接口都会经过此，
 * 我们可以在这里记录请求参数、响应内容，或者处理前后端分离情况下，
 * 以token换用户权限信息，token是否过期，
 * 请求头类型是否正确，防止非法请求等等
 */
@Slf4j
@Component
public class MyAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;

    protected MyAuthenticationFilter(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("MyAuthenticationFilter----------触发");

//        System.out.println("请求头类型： " + request.getContentType());
//        if ((request.getContentType()
//                == null
//                && request.getContentLength() > 0)
//                || (request.getContentType()
//                != null
//                && !request.getContentType().contains("application/json"))) {
//            filterChain.doFilter(request, response);
//            return;
//        }


        /**
         * 请求的HttpServletRequest流只能读一次，下一次就不能读取了，
         * 因此这里要使用自定义的MultiReadHttpServletRequest工具解决流只能读一次的问题，响应同理，
         */
        MultiReadHttpServletRequest wrappedRequest = new MultiReadHttpServletRequest(request);
        MultiReadHttpServletResponse wrappedResponse = new MultiReadHttpServletResponse(response);

        /**
         * 检查url，查看匿名可通过的url
         */


        String token = wrappedRequest.getHeader("X-Token");

        if (StringUtils.isNotBlank(token)) {

            // 检查token
            SecurityUser securityUser = userDetailsService.getUserByToken(token);
            if (securityUser == null || securityUser.getCurrentUserInfo() == null) {
                throw new AccessDeniedException("TOKEN已过期，请重新登录！");
            }
            //securityUser.getAuthorities()这个是UrlAccessDecisionManager的authentication权限来源
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(securityUser, null, securityUser.getAuthorities());
            // 全局注入角色权限信息和登录用户基本信息
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(wrappedRequest, wrappedResponse);
    }
}
