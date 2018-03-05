package com.grandland.qdhx.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grandland.qdhx.core.domain.GlSysDept;
import com.grandland.qdhx.core.mapper.GlSysDeptMapper;
import com.grandland.qdhx.web.service.GlSysDeptService;
import com.grandland.qdhx.web.service.base.BaseServiceImpl;

/**
 * 部门管理的Servlet类
 * @author loverqi
 * @date 2018年1月5日
 */
@Service
public class GlSysDeptServiceImpl extends BaseServiceImpl<GlSysDept> implements GlSysDeptService {

    @Autowired
    private GlSysDeptMapper glSysDeptMapper;

    /* 
     * 查询所有的部门简化信息
     * @see com.grandland.qdhx.web.service.GlSysDeptService#selectSimplifyDepts()
     */
    @Override
    public List<Map<String, String>> selectSimplifyDepts() {
        List<Map<String, String>> simplifyDepts = glSysDeptMapper.selectSimplifyDepts();

        return simplifyDepts;
    }

}
