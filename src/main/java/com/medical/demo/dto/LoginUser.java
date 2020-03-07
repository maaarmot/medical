package com.medical.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medical.demo.model.SysPermission;
import com.medical.demo.model.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class LoginUser extends SysUser implements UserDetails {

    private List<SysPermission> permissions;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.parallelStream().filter(p->!StringUtils.isEmpty(p.getPermission()))
                .map(p->new SimpleGrantedAuthority(p.getPermission())).collect(Collectors.toSet());
    }

    // 账户是否未过期
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未锁定
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return getStatus()!=2;
    }

    // 密码是否未过期
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账户是否激活
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
