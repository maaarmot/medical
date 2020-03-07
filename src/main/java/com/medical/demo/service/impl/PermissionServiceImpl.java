package com.medical.demo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.medical.demo.mapper.PermissionDao;
import com.medical.demo.base.result.Results;
import com.medical.demo.model.SysPermission;
import com.medical.demo.service.PermissionService;
import org.springframework.stereotype.Service;
import com.medical.demo.util.TreeUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionDao permissionDao;

    @Override
    public Results getMenu(Integer userId) {
        List<SysPermission> datas = permissionDao.listByUserId(userId);
        //把按钮过滤掉
        datas = datas.stream().filter(p -> p.getType().equals(1)).collect(Collectors.toList());
        JSONArray array = new JSONArray();
        TreeUtils.setPermissionsTree(0, datas, array);
        return Results.success(array);
    }

}
