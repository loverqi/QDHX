package com.grandland.qdhx.core.mapper;

import com.grandland.qdhx.core.domain.GlCronTask;
import com.grandland.qdhx.core.mybaties.mapper.BaseMapper;
import com.grandland.qdhx.core.utils.TableName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CronTaskMapper extends BaseMapper<GlCronTask> {

	@Select("SELECT * FROM " + TableName.CRON_TASK + " WHERE enable=#{enable}")
	List<GlCronTask> findByEnable(@Param("enable") boolean enable);

    @Select("SELECT * FROM " + TableName.CRON_TASK)
    List<GlCronTask> findAll();

    @Update("UPDATE " + TableName.CRON_TASK + " SET run_now=#{runNow} where id=#{id}")
    void updateRunNow(@Param("id") int id, @Param("runNow") boolean runNow);
	
}
