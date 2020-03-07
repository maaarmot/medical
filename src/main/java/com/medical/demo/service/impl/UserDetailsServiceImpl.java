package com.medical.demo.service.impl;

import com.medical.demo.dto.LoginUser;
import com.medical.demo.mapper.PermissionDao;
import com.medical.demo.model.SysUser;
import com.medical.demo.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;
    @Resource
    private PermissionDao permissionDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getSysUserByUsername(username);
        if(sysUser==null){
            throw new AuthenticationCredentialsNotFoundException("用户名不存在");
        }else if(sysUser.getStatus()==2){
            throw new LockedException("用户被锁定，请联系管理员");
        }

        LoginUser loginUser=new LoginUser();
        BeanUtils.copyProperties(sysUser,loginUser);

        loginUser.setPermissions(permissionDao.listByUserId(sysUser.getId()));
        return loginUser;
    }
}
