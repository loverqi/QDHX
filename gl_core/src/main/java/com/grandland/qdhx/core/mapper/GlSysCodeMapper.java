package com.grandland.qdhx.core.mapper;

import com.grandland.qdhx.core.domain.GlSysCode;
import com.grandland.qdhx.core.mybaties.mapper.BaseMapper;
import com.grandland.qdhx.core.utils.TableName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GlSysCodeMapper extends BaseMapper<GlSysCode> {

	@Select("SELECT * FROM " + TableName.SYS_CODE + " WHERE enable=#{enable} ORDER BY order_number")
	List<GlSysCode> findByEnable(@Param("enable") boolean enable);
	
}
