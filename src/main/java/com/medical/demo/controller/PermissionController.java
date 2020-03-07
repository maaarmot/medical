package com.medical.demo.controller;

import com.medical.demo.base.result.Results;
import com.medical.demo.model.SysPermission;
import com.medical.demo.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/menu")
    @ResponseBody
    public Results<SysPermission> getMenu(Integer userId){
        return permissionService.getMenu(userId);
    }
}
