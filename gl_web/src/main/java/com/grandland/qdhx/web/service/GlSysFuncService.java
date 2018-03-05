package com.grandland.qdhx.web.service;

import java.util.List;

import com.grandland.qdhx.core.domain.GlSysFunc;
import com.grandland.qdhx.web.service.base.BaseService;

/**
 * 菜单管理的Servlet类
 * @author loverqi
 * @date 2018年1月5日
 */
public interface GlSysFuncService extends BaseService<GlSysFunc> {

    /**
     * 根据菜单的父级id查询指定的菜单项，不自动加载子层菜单
     * @param parentId 菜单的id
     * @return 查询到的菜单项
     */
    List<GlSysFunc> selectByParentByPrimaryKey(String parentId);

    /**
     * 查询所有的顶层目录，不自动加载子层菜单
     * @return 所有的顶层目录
     */
    List<GlSysFunc> selectGlSysFuncs();

    /**
     * 查询所有的顶层目录，自动加载所有可能的子层菜单
     * @return 所有的顶层目录
     */
    List<GlSysFunc> selectGlSysFuncsWithChildren();

    /**
     * 查询所有已经授权的目录，自动加载所有可能的子层菜单,
     * @return 所有的顶层目录
     */
    List<GlSysFunc> selectWithPriv(List<String> privs);

}
