package com.medical.demo.service.impl;

import com.medical.demo.base.result.Results;
import com.medical.demo.dto.DoctorDTO;
import com.medical.demo.mapper.DepartmentMapper;
import com.medical.demo.mapper.DoctorMapper;
import com.medical.demo.mapper.SysUserMapper;
import com.medical.demo.model.Doctor;
import com.medical.demo.model.DoctorExample;
import com.medical.demo.model.SysUser;
import com.medical.demo.service.DoctorService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private SysUserServiceImpl sysUserServiceImpl;

    @Override
    public List<DoctorDTO> getAllDoctorsByPage(Integer offset, Integer limit) {

        List<Doctor> doctorList = doctorMapper.selectByExampleWithRowbounds(new DoctorExample(), new RowBounds(offset, limit));
        List<DoctorDTO> doctorDTOList=new ArrayList<>();
        for(Doctor doctor:doctorList){
            SysUser sysUser = sysUserMapper.selectByPrimaryKey(doctor.getUserid());
            DoctorDTO doctorDTO=new DoctorDTO();
            BeanUtils.copyProperties(doctor,doctorDTO);
            doctorDTO.setSysUser(sysUser);
            doctorDTO.setDepName(departmentMapper.selectByPrimaryKey(doctor.getDepid()).getName());
            doctorDTOList.add(doctorDTO);
        }
        return doctorDTOList;
    }

    @Override
    public Long countAllDoctors() {
        return doctorMapper.countByExample(new DoctorExample());
    }

    @Override
    public Results save(SysUser sysUser, Doctor doctor ,Integer roleId) {
        sysUserServiceImpl.save(sysUser,roleId);
        doctor.setUserid(sysUser.getId());
        doctorMapper.insert(doctor);
        return Results.success();
    }

    @Override
    public Results update(SysUser sysUser, Doctor doctor) {
        doctorMapper.updateByPrimaryKeySelective(doctor);
        sysUser.setId(doctor.getUserid());
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return Results.success();
    }

    @Override
    public int deleteDoctor(Integer docId) {
        //删除sys_user表
        Doctor doctor = doctorMapper.selectByPrimaryKey(docId);
        sysUserServiceImpl.deleteSysUser(doctor.getUserid());
        //删除doctor表
        return doctorMapper.deleteByPrimaryKey(docId);
    }

}
