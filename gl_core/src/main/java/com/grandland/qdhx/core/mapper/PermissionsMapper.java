package com.grandland.qdhx.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.grandland.qdhx.core.domain.Permissions;
import com.grandland.qdhx.core.mybaties.mapper.BaseMapper;
import com.grandland.qdhx.core.provider.PermissionsSqlProvider;
import com.grandland.qdhx.core.utils.TableName;

/**
 * 权限的的管理Mapper
 * @author loverqi
 * @date 2018年1月5日
 */
public interface PermissionsMapper extends BaseMapper<Permissions> {

    @SelectProvider(type = PermissionsSqlProvider.class, method = "selectGlSysFuncsWithPrivs")
    List<Permissions> selectGlSysFuncsWithPrivs(Integer postId);

    @Select("SELECT DISTINCT f.*, ifnull(p.`enable`, 0) AS `is_selected`" + " FROM " + TableName.SYS_FUNC
            + " AS f JOIN " + TableName.SYS_PRIV + " AS p ON  p.func_id = f.id"
            + " WHERE p.post_id = #{postId} AND f.`enable` = TRUE AND f.is_juris = TRUE AND p.`enable` = TRUE")
    List<Permissions> selectPrivsByPostId(Integer postId);

}