package com.grandland.qdhx.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.grandland.qdhx.core.domain.Level;
import com.grandland.qdhx.core.mybaties.mapper.BaseMapper;
import com.grandland.qdhx.core.provider.LevelSqlProvider;

/**
 * 权限的的管理Mapper
 * @author loverqi
 * @date 2018年1月5日
 */
public interface LevelMapper extends BaseMapper<Level> {

    /**
     * 查询部门岗位层级菜单，包括用户
     */
    @SelectProvider(type = LevelSqlProvider.class, method = "selectPostLevel")
    List<Level> selectPostLevel(@Param("deptName") String deptName, @Param("postName") String postName,
            @Param("realName") String realName);

    /**
     * 查询部门岗位层级菜单，不包括用户
     */
    @SelectProvider(type = LevelSqlProvider.class, method = "selectPostLevelNotUser")
    List<Level> selectPostLevelNotUser(@Param("deptName") String deptName, @Param("postName") String postName);

}