package com.medical.demo.service;


import com.medical.demo.base.result.Results;
import com.medical.demo.dto.DoctorDTO;
import com.medical.demo.model.Doctor;
import com.medical.demo.model.SysUser;

import java.util.List;

public interface DoctorService {
    List<DoctorDTO> getAllDoctorsByPage(Integer offset, Integer limit);

    Long countAllDoctors();

    Results save(SysUser sysUser, Doctor doctor,Integer roleId);

    Results update(SysUser sysUser, Doctor doctor);

    int deleteDoctor(Integer docId);
}
