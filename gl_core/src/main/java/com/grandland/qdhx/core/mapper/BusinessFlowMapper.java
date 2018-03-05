package com.grandland.qdhx.core.mapper;

import com.grandland.qdhx.core.domain.BusinessFlow;
import com.grandland.qdhx.core.mybaties.mapper.BaseMapper;
import com.grandland.qdhx.core.utils.TableName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BusinessFlowMapper extends BaseMapper<BusinessFlow> {

    @Select("SELECT DISTINCT create_by FROM " + TableName.BS_FLOW + " WHERE bumor_id=#{id}")
	List<String> findDistinctOperatorById(@Param("id") String id);

}
