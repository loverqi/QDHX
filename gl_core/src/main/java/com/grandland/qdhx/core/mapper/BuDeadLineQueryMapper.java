package com.grandland.qdhx.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.grandland.qdhx.core.domain.param.BuDeadLineQuParam;
import com.grandland.qdhx.core.provider.BuDeadLineQueryProvider;

public interface BuDeadLineQueryMapper {
	
	
	@SelectProvider(type = BuDeadLineQueryProvider.class, method = "query")
	
	 List<Map<String,String>> query(@Param("buDeadLineQuParam") BuDeadLineQuParam buDeadLineQuParam);

}
