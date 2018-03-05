package com.grandland.qdhx.core.mapper;

import com.grandland.qdhx.core.domain.BusinessInspectionDetail;
import com.grandland.qdhx.core.domain.param.BsInspectionQueryParam;
import com.grandland.qdhx.core.mybaties.mapper.BaseMapper;
import com.grandland.qdhx.core.provider.BsInpectionSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BsInspectionDetailMapper extends BaseMapper<BusinessInspectionDetail>{

    @SelectProvider(type = BsInpectionSqlProvider.class, method = "queryDetail")
    List<Map<String,String>> queryDetail(@Param("param") BsInspectionQueryParam param);


    @InsertProvider(type = BsInpectionSqlProvider.class, method = "insertDetail")
    @Options(useGeneratedKeys = true, keyProperty = "inspectionDetail.id")
    int insertDetail(@Param("inspectionDetail") BusinessInspectionDetail inspectionDetail);


    @UpdateProvider(type = BsInpectionSqlProvider.class, method = "updateDetail")
    int updateDetail(@Param("inspectionDetail") BusinessInspectionDetail inspectionDetail);

}

