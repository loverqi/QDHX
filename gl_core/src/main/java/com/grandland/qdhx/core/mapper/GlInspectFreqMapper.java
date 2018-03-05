package com.grandland.qdhx.core.mapper;

import com.grandland.qdhx.core.domain.GlInspectFreq;
import com.grandland.qdhx.core.mybaties.mapper.BaseMapper;
import com.grandland.qdhx.core.provider.GlInspectFreqSqlProvider;
import com.grandland.qdhx.core.utils.TableName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface GlInspectFreqMapper extends BaseMapper<GlInspectFreq> {

	@Select("SELECT * FROM " + TableName.BS_INSPECT_FREQ + " WHERE is_deleted=#{isDeleted}")
	List<GlInspectFreq> findByIsDeleted(@Param("isDeleted") boolean isDeleted);

	/**
	 * 通过监管单位编号查找对应的巡核频率
	 * @param superCode
	 * @param isDeleted
	 * @return
	 */
	@SelectProvider(type = GlInspectFreqSqlProvider.class, method = "findBySuperCode")
	List<GlInspectFreq> findBySuperCode(@Param("superCode") String superCode, @Param("isDeleted") Boolean isDeleted);
	
}
