package com.medical.demo.service.impl;

import com.medical.demo.base.result.Results;
import com.medical.demo.mapper.SysRoleUserMapper;
import com.medical.demo.mapper.SysUserMapper;
import com.medical.demo.model.SysRoleUserExample;
import com.medical.demo.model.SysRoleUserKey;
import com.medical.demo.model.SysUser;
import com.medical.demo.model.SysUserExample;
import com.medical.demo.service.SysUserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public List<SysUser> getAllSysUsersByPage(Integer offset,Integer limit) {
        return sysUserMapper.selectByExampleWithRowbounds(new SysUserExample(),new RowBounds(offset,limit));
    }

    @Override
    public Long countAllSysUsers() {
        return sysUserMapper.countByExample(new SysUserExample());
    }

    @Override
    public Results save(SysUser sysUser,Integer roleId) {
        if(roleId!=null){

            sysUser.setStatus(1);
//            sysUser.setPassword(MD5.crypt(sysUser.getPassword()));
            sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
            sysUser.setCreatetime(new Date());
            sysUser.setUpdatetime(new Date());

            //插入sys_user表
            sysUserMapper.insert(sysUser);

            //插入sys_role_user表
            SysRoleUserKey sysRoleUserKey=new SysRoleUserKey();
            sysRoleUserKey.setUserid(sysUser.getId());
            sysRoleUserKey.setRoleid(roleId);
            sysRoleUserMapper.insert(sysRoleUserKey);
            return Results.success();
        }
        return Results.failure();
    }

    @Override
    public SysUser getSysUserByPhone(String telephone) {
        SysUserExample example = new SysUserExample();
        example.createCriteria()
                .andTelephoneEqualTo(telephone);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        if (sysUsers.size()==0){
            return null;
        }else {
            return sysUsers.get(0);
        }
    }

    @Override
    public int deleteSysUser(Integer sysUserId) {

        //删除sys_role_user表
        SysRoleUserExample example = new SysRoleUserExample();
        example.createCriteria()
                .andUseridEqualTo(sysUserId);
        sysRoleUserMapper.deleteByExample(example);

        //删除sys_user表
        return sysUserMapper.deleteByPrimaryKey(sysUserId);
    }

    @Override
    public SysUser getSysUserByUsername(String username) {
        SysUserExample example = new SysUserExample();
        example.createCriteria()
                .andUsernameEqualTo(username);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        if(sysUsers.size()==0){
            return null;
        }
        return sysUsers.get(0);
    }

}
