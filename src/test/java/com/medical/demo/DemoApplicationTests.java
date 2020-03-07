package com.medical.demo;

import com.medical.demo.mapper.SysUserMapper;
import com.medical.demo.model.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
class DemoApplicationTests {

    @Resource
    private SysUserMapper sysUserMapper;

    @Test
    void contextLoads() {
//        SysUser sysUser = new SysUser();
//        sysUser.setUsername("肥圆管理");
//        sysUser.setPassword(new BCryptPasswordEncoder().encode("12345"));
//        sysUser.setNickname("王晓红");
//        sysUser.setTelephone("15845637902");
//        sysUser.setStatus(1);
//        sysUser.setCreatetime(new Date());
//        sysUser.setUpdatetime(new Date());
//        sysUserMapper.insert(sysUser);

        SysUser sysUser = new SysUser();
        sysUser.setUsername("圆润医生");
        sysUser.setPassword(new BCryptPasswordEncoder().encode("123"));
        sysUser.setNickname("张翠花");
        sysUser.setTelephone("13266789906");
        sysUser.setStatus(1);
        sysUser.setCreatetime(new Date());
        sysUser.setUpdatetime(new Date());
        sysUserMapper.insert(sysUser);
    }

}
