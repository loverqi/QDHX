package com.grandland.qdhx.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.grandland.qdhx.core.domain.GlSysFunc;
import com.grandland.qdhx.core.mybaties.mapper.BaseMapper;
import com.grandland.qdhx.core.provider.GlSysFuncSqlProvider;

/**
 * 菜单表的管理Mapper
 * @author loverqi
 * @date 2018年1月5日
 */
public interface GlSysFuncMapper extends BaseMapper<GlSysFunc> {

    @SelectProvider(type = GlSysFuncSqlProvider.class, method = "selectWithPriv")
    List<GlSysFunc> selectWithPriv(@Param("privs") List<String> privs);
}