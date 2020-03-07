package com.medical.demo.controller;

import com.medical.demo.base.result.PageTableRequest;
import com.medical.demo.base.result.Results;
import com.medical.demo.model.SysUser;
import com.medical.demo.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/list")
    @ResponseBody
    public Results<SysUser> getAllSysUser(PageTableRequest request){
        request.countOffset();
        List<SysUser> list = sysUserService.getAllSysUsersByPage(request.getOffset(), request.getLimit());
        Long count = sysUserService.countAllSysUsers();
        return Results.success("success",count.intValue(), list);
    }


}
