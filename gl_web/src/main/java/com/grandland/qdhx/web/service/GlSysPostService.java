package com.grandland.qdhx.web.service;

import java.util.List;
import java.util.Map;

import com.grandland.qdhx.core.bean.ResponsePageDate;
import com.grandland.qdhx.core.domain.GlSysPost;
import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.web.service.base.BaseService;

/**
 * 岗位管理的Servlet类
 * @author loverqi
 * @date 2018年1月5日
 */
public interface GlSysPostService extends BaseService<GlSysPost> {

    /**
     * 查询简化后的部门信息
     * @return
     */
    List<Map<String, String>> selectSimplifyPosts(Integer deptId);

    /**
     * 根据条件查询对象的方法, 支持分页
     * @param example 指定的条件
     * @return 所有符合条件的对象
     */
    ResponsePageDate<GlSysPost> selectByExampleWithRowboundsAndDeptName(GlSysPost glSysPost, Example example, int page,
            int pageSize);

}
