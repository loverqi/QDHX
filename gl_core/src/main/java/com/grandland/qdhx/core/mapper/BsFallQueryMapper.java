package com.grandland.qdhx.core.mapper;

import com.grandland.qdhx.core.domain.param.BsFallQueryParam;
import com.grandland.qdhx.core.provider.BuFallQuerySqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface BsFallQueryMapper {
	
	 @SelectProvider(type = BuFallQuerySqlProvider.class, method = "query")
	    List<Map<String,String>> query(@Param("bsFallQueryParam") BsFallQueryParam bsFallQueryParam);
	
}
