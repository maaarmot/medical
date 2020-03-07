package com.medical.demo.controller;

import com.medical.demo.base.result.PageTableRequest;
import com.medical.demo.base.result.ResponseCode;
import com.medical.demo.base.result.Results;
import com.medical.demo.dto.DoctorDTO;
import com.medical.demo.mapper.DoctorMapper;
import com.medical.demo.mapper.SysUserMapper;
import com.medical.demo.model.Doctor;
import com.medical.demo.model.SysUser;
import com.medical.demo.service.DoctorService;
import com.medical.demo.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/doc")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private SysUserService sysUserService;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private DoctorMapper doctorMapper;

    @GetMapping("/list")
    @ResponseBody
    public Results<DoctorDTO> getAllDoctor(PageTableRequest request){
        request.countOffset();
        List<DoctorDTO> dtoList = doctorService.getAllDoctorsByPage(request.getOffset(), request.getLimit());
        Long count = doctorService.countAllDoctors();
        return Results.success("success",count.intValue(), dtoList);
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('sys:doc:add')")
    public String addDoctor(Model model){
        model.addAttribute(new SysUser());
        model.addAttribute(new Doctor());
        return "doc/doc-add";
    }

    @PostMapping("/add")
    @ResponseBody
    public Results<DoctorDTO> saveDoctor(SysUser sysUser, Doctor doctor){

        //检查手机号是否已被注册
        SysUser user=sysUserService.getSysUserByPhone(sysUser.getTelephone());
        if(user!=null){
            log.info("手机号已被注册");
            return Results.failure(ResponseCode.PHONE_REPEAT.getCode(),ResponseCode.PHONE_REPEAT.getMessage());
        }

        Integer roleId=2;
        return doctorService.save(sysUser,doctor,roleId);
    }

    String pattern="yyyy-MM-dd";
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request){
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat(pattern),true));
    }

    @GetMapping("/edit")
    public String editDoctor(Model model,Doctor doctor){
        Doctor o = doctorMapper.selectByPrimaryKey(doctor.getId());
        model.addAttribute(o);
        model.addAttribute(sysUserMapper.selectByPrimaryKey(o.getUserid()));
        return "doc/doc-edit";
    }

    @PostMapping("/edit")
    @ResponseBody
    public Results<DoctorDTO> updateDoctor(SysUser sysUser, Doctor doctor){
        return doctorService.update(sysUser,doctor);
    }

    @GetMapping("/delete")
    @ResponseBody
    public Results delDoctor(Doctor doctor){
        int count=doctorService.deleteDoctor(doctor.getId());
        if(count>0){
            return Results.success();
        }else{
            return Results.failure();
        }
    }
}
