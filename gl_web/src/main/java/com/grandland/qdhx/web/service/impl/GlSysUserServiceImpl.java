package com.grandland.qdhx.web.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.grandland.qdhx.core.bean.ResponsePageDate;
import com.grandland.qdhx.core.domain.GlSysUser;
import com.grandland.qdhx.core.mapper.GlSysPrivMapper;
import com.grandland.qdhx.core.mapper.GlSysUserMapper;
import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.web.service.GlSysUserService;
import com.grandland.qdhx.web.service.base.BaseServiceImpl;

/**
 * 用户管理的Servlet类
 * @author loverqi
 * @date 2018年1月5日
 */
@Service
public class GlSysUserServiceImpl extends BaseServiceImpl<GlSysUser> implements UserDetailsService, GlSysUserService {

    @Autowired
    private GlSysUserMapper glSysUserMapper;

    @Autowired
    private GlSysPrivMapper glSysPrivMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根据用户名加载用户的方法
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        GlSysUser glSysUser = new GlSysUser();

        Example example = new Example();

        example.createCriteria().andFieldEqualTo("username", username).andFieldEqualTo("enable", true);
        List<GlSysUser> glSysUsers = baseMapper.selectByExample(glSysUser, example);

        if (glSysUsers.size() > 0) {
            glSysUser = glSysUsers.get(0);

            Integer postId = glSysUser.getPostId();

            //查询用户的角色信息，并返回存入user中
            if (postId != null) {
                List<String> privs = glSysPrivMapper.selectPrivByPostId(postId);

                glSysUser.addAuthoritiesGlSysPrivs(privs);
            }

        }

        return glSysUser;
    }

    @Override
    public int insertSelective(GlSysUser record) {

        //启用账户
        record.setEnable(true);
        //设置账户创建时间
        record.setUpdateTime(new Date());
        record.setPassword(passwordEncoder.encode(record.getPassword()));
        return super.insertSelective(record);
    }

    /**
     * 根据条件查询用户列表，并填充外键的Name
     * @param glSysUser 查询的对象
     * @param example 查询条件
     * @return
     */
    @Override
    public ResponsePageDate<GlSysUser> selectByExampleWithRowboundsAndConnectionName(GlSysUser glSysUser,
            Example example, int page, int pageSize) {
        ResponsePageDate<GlSysUser> pageDate = super.selectByExampleWithRowbounds(glSysUser, example, page, pageSize);
        List<GlSysUser> glSysUsers = pageDate.getList();

        setConnectionName(glSysUsers);

        return pageDate;
    }

    /*
     * 修改用户密码
     * @see com.grandland.qdhx.web.service.GlSysUserService#uodatePasswordByPrimaryKey(com.grandland.qdhx.core.domain.GlSysUser)
     */
    @Override
    public int updatePasswordByPrimaryKey(GlSysUser record) {

        String password = record.getPassword();
        record.setPassword(passwordEncoder.encode(password));
        int count = baseMapper.updateByPrimaryKeySelective(record);

        return count;
    }

    @Override
    public List<Map<String, String>> selectSimplifyUsers(Integer postId) {
        List<Map<String, String>> selectSimplifyUsers = glSysUserMapper.selectSimplifyUsers(postId);

        return selectSimplifyUsers;
    }

    @Override
    public List<GlSysUser> selectByExampleAndConnectionName(GlSysUser t, Example example) {

        List<GlSysUser> glSysUsers = super.selectByExample(t, example);

        //填充外键名字字段 
        setConnectionName(glSysUsers);

        return glSysUsers;
    }

    /**
     * 填充外键字段的方法
     */
    private void setConnectionName(List<GlSysUser> glSysUsers) {
        for (GlSysUser glSysUserTemp : glSysUsers) {
            Integer postId = glSysUserTemp.getPostId();
            if (postId != null) {
                Map<String, String> maps = glSysUserMapper.selectDeptByPostId(postId);
                if (maps != null && maps.size() > 0) {
                    String deptName = maps.get("deptName");
                    glSysUserTemp.setFieldValueByKey("deptName", deptName);
                    String postName = maps.get("postName");
                    glSysUserTemp.setFieldValueByKey("postName", postName);
                }
            }
        }
    }

}
