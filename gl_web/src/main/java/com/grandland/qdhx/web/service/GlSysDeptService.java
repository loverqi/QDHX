package com.grandland.qdhx.web.service;

import java.util.List;
import java.util.Map;

import com.grandland.qdhx.core.domain.GlSysDept;
import com.grandland.qdhx.web.service.base.BaseService;

/**
 * 部门管理的Servlet类
 * @author loverqi
 * @date 2018年1月5日
 */
public interface GlSysDeptService extends BaseService<GlSysDept> {

    /**
     * 获取所有部门的简化信息
     */
    List<Map<String, String>> selectSimplifyDepts();

}
