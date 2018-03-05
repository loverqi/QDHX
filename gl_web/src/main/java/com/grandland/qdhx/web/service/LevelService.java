package com.grandland.qdhx.web.service;

import java.util.List;

import com.grandland.qdhx.core.domain.Level;

/**
 * 层级菜单管理的Servlet类
 * @author loverqi
 * @date 2018年2月6日
 */
public interface LevelService {

    /**
     * 查询并拼装用户层级关系
     * @param deptName 模糊查询 部门名称
     * @param postName 模糊查询 岗位名称
     * @param username 模糊查询 用户名
     * @return 拼装好的层级菜单
     */
    List<Level> getPostLevel(String deptName, String postName, String realName);

    /**
     * 查询并拼装岗位层级关系
     * @param deptName 模糊查询 部门名称
     * @param postName 模糊查询 岗位名称
     * @param username 模糊查询 用户名
     * @return 拼装好的层级菜单
     */
    List<Level> getPostLevelNotUser(String deptName, String postName);

}
