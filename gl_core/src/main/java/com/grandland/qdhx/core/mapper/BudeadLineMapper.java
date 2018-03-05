package com.grandland.qdhx.core.mapper;

import com.grandland.qdhx.core.domain.BudeadLine;
import com.grandland.qdhx.core.mybaties.mapper.BaseMapper;
import com.grandland.qdhx.core.provider.BuDeadLineQueryProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

public interface BudeadLineMapper extends BaseMapper<BudeadLine>{

    @InsertProvider(type = BuDeadLineQueryProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "budeadLine.id")
    int insert(@Param("budeadLine") BudeadLine budeadLine);

}
