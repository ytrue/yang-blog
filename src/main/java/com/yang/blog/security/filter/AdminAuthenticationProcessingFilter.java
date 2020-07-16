package com.yang.blog.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.yang.blog.dto.AdminLoginParam;
import com.yang.blog.security.login.AdminAuthenticationFailureHandler;
import com.yang.blog.security.login.AdminAuthenticationSuccessHandler;
import com.yang.blog.security.login.CusAuthenticationManager;
import com.yang.blog.util.MultiReadHttpServletRequest;
import com.yang.blog.util.ResponseData;
import com.yang.blog.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.ArrayList;
import java.util.Set;

/**
 * <p> 自定义用户密码校验过滤器 </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2019/10/12 15:32
 */
@Slf4j
@Component
public class AdminAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * @param authenticationManager:             认证管理器
     * @param adminAuthenticationSuccessHandler: 认证成功处理
     * @param adminAuthenticationFailureHandler: 认证失败处理
     */
    public AdminAuthenticationProcessingFilter(CusAuthenticationManager authenticationManager, AdminAuthenticationSuccessHandler adminAuthenticationSuccessHandler, AdminAuthenticationFailureHandler adminAuthenticationFailureHandler) {
        super(new AntPathRequestMatcher("/admin/login", "POST"));
        this.setAuthenticationManager(authenticationManager);
        this.setAuthenticationSuccessHandler(adminAuthenticationSuccessHandler);
        this.setAuthenticationFailureHandler(adminAuthenticationFailureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (request.getContentType() == null || !request.getContentType().contains("application/json")) {
            throw new AuthenticationServiceException("请求头类型不支持: " + request.getContentType());
        }

        log.info("触发AdminAuthenticationProcessingFilter....");

        UsernamePasswordAuthenticationToken authRequest;
        try {
            MultiReadHttpServletRequest wrappedRequest = new MultiReadHttpServletRequest(request);
            // 将前端传递的数据转换成jsonBean数据格式
            AdminLoginParam adminLogin = JSONObject.parseObject(wrappedRequest.getBodyJsonStrByJson(wrappedRequest), AdminLoginParam.class);
            /**
             * 数据校验
             */
            Set<ConstraintViolation<AdminLoginParam>> validateSet = Validation.buildDefaultValidatorFactory()
                    .getValidator()
                    .validate(adminLogin);

            if (!CollectionUtils.isEmpty(validateSet)) {
                ArrayList<String> errorList = new ArrayList<>();
                validateSet.forEach(adminLoginConstraintViolation -> errorList.add(adminLoginConstraintViolation.getMessageTemplate()));
                ResponseUtils.out(response, ResponseData.fail(2, "error", errorList));
                return null;
            }
            authRequest = new UsernamePasswordAuthenticationToken(adminLogin.getUsername(), adminLogin.getPassword(), null);
            authRequest.setDetails(authenticationDetailsSource.buildDetails(wrappedRequest));
        } catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage());
        }
        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
