package com.grandland.qdhx.web.service;

import java.util.List;
import java.util.Map;

import com.grandland.qdhx.core.bean.ResponsePageDate;
import com.grandland.qdhx.core.domain.GlSysUser;
import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.web.service.base.BaseService;

/**
 * 用户管理的Servlet类
 * @author loverqi
 * @date 2018年1月5日
 */
public interface GlSysUserService extends BaseService<GlSysUser> {

    /**
     * 根据条件查询用户列表，并填充外键的Name
     */
    ResponsePageDate<GlSysUser> selectByExampleWithRowboundsAndConnectionName(GlSysUser glSysUser, Example example,
            int page, int pageSize);

    /**
     * 根据条件查询用户列表，并填充外键的Name
     */
    List<GlSysUser> selectByExampleAndConnectionName(GlSysUser t, Example example);

    /**
     * 修改账户密码
     */
    int updatePasswordByPrimaryKey(GlSysUser record);

    /**
     * 查询简化后的用户信息
     * */
    List<Map<String, String>> selectSimplifyUsers(Integer postId);
}
