package com.grandland.qdhx.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.grandland.qdhx.core.domain.GlSysPost;
import com.grandland.qdhx.core.mybaties.mapper.BaseMapper;
import com.grandland.qdhx.core.mybaties.mapper.BaseTemplate;
import com.grandland.qdhx.core.utils.TableName;

/**
 * 岗位对象管理Mapper
 * @author loverqi
 * @date 2018年1月5日
 */
public interface GlSysPostMapper extends BaseMapper<GlSysPost> {

    /**
     * 查询简化后的岗位信息
     * @return
     */
    @Select("SELECT p.id AS id, p.post_name AS name" + " FROM " + TableName.SYS_POST + " AS p"
            + " WHERE p.`enable` = true AND p.dept_id = #{deptId}")
    List<Map<String, String>> selectSimplifyPosts(Integer deptId);

    /**
     * 插入对象，仅插入对象非空的属性
     * @param record 需要插入的对象
     * @return 插入数据的id
     */
    @InsertProvider(type = BaseTemplate.class, method = "insertSelective")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertSelective(GlSysPost record);
}