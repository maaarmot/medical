package com.medical.demo.controller;

import com.medical.demo.base.result.Results;
import com.medical.demo.model.Department;
import com.medical.demo.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/dep")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/menu")
    @ResponseBody
    public Results<Department> getMenu(){
        return departmentService.getMenu();
    }
}
