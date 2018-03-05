package com.grandland.qdhx.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.grandland.qdhx.core.domain.GlSysPriv;
import com.grandland.qdhx.core.mybaties.mapper.BaseMapper;
import com.grandland.qdhx.core.utils.TableName;

/**
 * 权限管理的Mapper
 * @author loverqi
 * @date 2018年1月5日
 */
public interface GlSysPrivMapper extends BaseMapper<GlSysPriv> {

    @Select("SELECT f.func_name FROM " + TableName.SYS_PRIV + " AS p INNER JOIN " + TableName.SYS_FUNC
            + " AS f ON f.id = p.func_id " + "WHERE p.post_id = #{postId} "
            + "AND p.`enable` = true AND f.`enable` = true AND f.is_juris = true")
    List<String> selectPrivByPostId(Integer postId);

    @Select("SELECT f.text FROM " + TableName.SYS_PRIV + " AS p INNER JOIN " + TableName.SYS_FUNC
            + " AS f ON f.id = p.func_id " + "WHERE p.post_id = #{postId} "
            + "AND p.`enable` = true AND f.`enable` = true AND f.is_juris = true")
    List<String> selectPrivTextByPostId(Integer postId);

}