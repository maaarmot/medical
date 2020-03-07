package com.medical.demo.service;

import com.medical.demo.base.result.Results;
import com.medical.demo.model.SysUser;

import java.util.List;

public interface SysUserService {
    List<SysUser> getAllSysUsersByPage(Integer offset,Integer limit);

    Long countAllSysUsers();

    Results save(SysUser sysUser,Integer roleId);

    int deleteSysUser(Integer sysUserId);

    SysUser getSysUserByPhone(String telephone);

    SysUser getSysUserByUsername(String username);
}
