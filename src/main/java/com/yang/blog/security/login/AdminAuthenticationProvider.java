package com.yang.blog.security.login;

import com.yang.blog.security.dto.SecurityUser;
import com.yang.blog.service.impl.UserDetailsServiceImpl;
import com.yang.blog.util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * <p> 自定义认证处理 </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2019/10/12 14:49
 */
@Component
@Slf4j
public class AdminAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取前端表单中输入后返回的用户名、密码
        String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();


        SecurityUser userInfo = (SecurityUser) userDetailsService.loadUserByUsername(userName);
        boolean isValid = PasswordUtil.isValidPassword(password, userInfo.getPassword(), userInfo.getCurrentUserInfo().getSalt());
        // 验证密码
        if (!isValid) {
            throw new BadCredentialsException("密码错误！");
        }

        UsernamePasswordAuthenticationToken authentication1 = new UsernamePasswordAuthenticationToken(userInfo, password, userInfo.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication1;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
