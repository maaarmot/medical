package com.medical.demo.service.impl;

import com.medical.demo.base.result.Results;
import com.medical.demo.mapper.DepartmentMapper;
import com.medical.demo.model.Department;
import com.medical.demo.model.DepartmentExample;
import com.medical.demo.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public Results getMenu() {
        List<Department> departments = departmentMapper.selectByExample(new DepartmentExample());
        return Results.success(0, departments);
    }
}
