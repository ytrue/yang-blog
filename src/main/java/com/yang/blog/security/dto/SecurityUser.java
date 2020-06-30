package com.yang.blog.security.dto;

import com.yang.blog.entity.Admin;
import com.yang.blog.entity.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author：yangyi
 * @date：2020/5/16 18:37
 * @description：
 */
@Data
public class SecurityUser implements UserDetails{

    /**
     * 当前登录用户
     */
    private transient Admin currentUserInfo;
    /**
     * 角色
     */
    private transient List<Role> roleList;

    public SecurityUser() {
    }

    public SecurityUser(Admin user) {
        if (user != null) {
            this.currentUserInfo = user;
        }
    }

    public SecurityUser(Admin user, List<Role> roleList) {
        if (user != null) {
            this.currentUserInfo = user;
            this.roleList = roleList;
        }
    }

    /**
     * 获取当前用户所具有的角色
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(this.roleList)) {
            for (Role role : this.roleList) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getCode());
                authorities.add(authority);
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return currentUserInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return currentUserInfo.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
