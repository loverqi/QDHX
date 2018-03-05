package com.grandland.qdhx.core.mapper;

import com.grandland.qdhx.core.domain.BusinessInspection;
import com.grandland.qdhx.core.domain.param.BsInspectionQueryParam;
import com.grandland.qdhx.core.mybaties.mapper.BaseMapper;
import com.grandland.qdhx.core.provider.BsInpectionSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BsInspectionMapper extends BaseMapper<BusinessInspection>{

    @SelectProvider(type = BsInpectionSqlProvider.class, method = "query")
    List<Map<String,String>> query(@Param("param") BsInspectionQueryParam param);


    @InsertProvider(type = BsInpectionSqlProvider.class, method = "insert")
    int insert(@Param("inspection") BusinessInspection inspection);

    @UpdateProvider(type = BsInpectionSqlProvider.class, method = "update")
    int update(@Param("inspection") BusinessInspection inspection);

}
