package com.grandland.qdhx.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.grandland.qdhx.core.domain.GlSysDept;
import com.grandland.qdhx.core.mybaties.mapper.BaseMapper;
import com.grandland.qdhx.core.utils.TableName;

/**
 * 部门对象管理Mapper
 * @author loverqi
 * @date 2018年1月5日
 */
public interface GlSysDeptMapper extends BaseMapper<GlSysDept> {
    /**
     * 查询简化后的部门信息
     * @return
     */
    @Select("SELECT d.id AS id, d.dept_name AS name FROM " + TableName.SYS_DEPT + " AS d   WHERE  d.`enable` = TRUE")
    List<Map<String, String>> selectSimplifyDepts();

    /**
     * 根据Id查询对应的部门和岗位的名称
     * @param postId 岗位名称
     * @return
     */
    @Select("SELECT d.dept_name AS deptName FROM " + TableName.SYS_DEPT
            + " AS d WHERE d.id = #{deptId}   AND d.`enable` = TRUE")
    Map<String, String> selectDeptNameById(Integer deptId);

}