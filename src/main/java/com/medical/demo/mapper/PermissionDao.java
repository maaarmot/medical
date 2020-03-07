package com.medical.demo.mapper;

import com.medical.demo.model.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {

    @Select("SELECT DISTINCT sp.*  " +
            "FROM sys_role_user sru " +
            "INNER JOIN sys_role_permission srp ON srp.roleId = sru.roleId " +
            "LEFT JOIN sys_permission sp ON srp.permissionId = sp.id " +
            "WHERE " +
            "sru.userId = #{userId}")
    List<SysPermission> listByUserId(@Param("userId") Integer userId);
}
