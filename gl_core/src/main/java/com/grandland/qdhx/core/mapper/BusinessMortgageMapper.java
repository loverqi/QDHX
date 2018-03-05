package com.grandland.qdhx.core.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import com.grandland.qdhx.core.domain.BusinessMortgage;
import com.grandland.qdhx.core.mybaties.mapper.BaseMapper;
import com.grandland.qdhx.core.mybaties.mapper.BaseTemplate;

public interface BusinessMortgageMapper extends BaseMapper<BusinessMortgage> {
	
	@InsertProvider(type = BaseTemplate.class, method = "insertSelective")
	@Options(useGeneratedKeys = true, keyProperty = "id")   
    int insertSelective(BusinessMortgage record);

}