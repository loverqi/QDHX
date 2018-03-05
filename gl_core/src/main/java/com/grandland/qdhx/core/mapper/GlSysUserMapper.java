package com.grandland.qdhx.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.grandland.qdhx.core.domain.GlSysUser;
import com.grandland.qdhx.core.mybaties.mapper.BaseMapper;
import com.grandland.qdhx.core.utils.TableName;

/**
 * 用户表的管理Mapper
 * @author loverqi
 * @date 2018年1月5日
 */
public interface GlSysUserMapper extends BaseMapper<GlSysUser> {

    /**
     * 根据Id查询对应的部门和岗位的名称
     * @param postId 岗位名称
     * @return
     */
    @Select("SELECT  d.dept_name AS deptName, p.post_name AS postName" + " FROM " + TableName.SYS_DEPT
            + " AS d INNER JOIN " + TableName.SYS_POST + " AS p ON p.dept_id = d.id" + " WHERE p.id = #{postId} "
            + " AND d.`enable` = TRUE AND p.`enable` = TRUE")
    Map<String, String> selectDeptByPostId(Integer postId);

    /**
     * 查询简化后的用户信息
     * @return
     */
    @Select("SELECT u.id AS id, u.username AS name" + " FROM " + TableName.SYS_USER + " AS u"
            + " WHERE u.`enable` = true AND u.post_id = #{postId}")
    List<Map<String, String>> selectSimplifyUsers(Integer postId);

}