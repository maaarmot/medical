package com.medical.demo.dto;

import com.medical.demo.model.SysUser;
import lombok.Data;

@Data
public class DoctorDTO {
    private Integer id;
    private Boolean sex;
    private Integer depid;
    private Integer posid;
    private String detail;
    private String intro;
    private Integer regcount;
    private Integer inqcount;
    private Integer userid;
    private SysUser sysUser;
    private String depName;

//    public String getNickname(){
//        return sysUser.getNickname();
//    }
}
