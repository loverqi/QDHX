package com.grandland.qdhx.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grandland.qdhx.core.domain.GlSysFunc;
import com.grandland.qdhx.core.mapper.GlSysFuncMapper;
import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.treedata.TreeBuilder;
import com.grandland.qdhx.web.service.GlSysFuncService;
import com.grandland.qdhx.web.service.base.BaseServiceImpl;

/**
 * 菜单管理的Servlet类
 * @author loverqi
 * @date 2018年1月5日
 */
@Service
public class GlSysFuncServiceImpl extends BaseServiceImpl<GlSysFunc> implements GlSysFuncService {

    @Autowired
    private GlSysFuncMapper glSysFuncMapper;

    /**
     * 根据菜单的父级id查询指定的菜单项，不自动加载子层菜单
     * @param parentId 菜单的id
     * @return 查询到的菜单项
     */
    @Override
    public List<GlSysFunc> selectByParentByPrimaryKey(String parentId) {
        GlSysFunc glSysFunc = new GlSysFunc();
        Example example = new Example();
        example.createCriteria().andFieldEqualTo("parent", parentId).andFieldEqualTo("enable", true)
                .andFieldEqualTo("isLeaf", false);
        List<GlSysFunc> glSysFuncs = baseMapper.selectByExample(glSysFunc, example);

        return glSysFuncs;
    }

    /**
     * 查询所有的顶层目录，不自动加载子层菜单
     * @return 所有的顶层目录
     */
    @Override
    public List<GlSysFunc> selectGlSysFuncs() {
        GlSysFunc glSysFunc = new GlSysFunc();
        Example example = new Example();
        example.createCriteria().andFieldEqualTo("enable", true).andFieldEqualTo("isLeaf", false)
                .andFieldEqualTo("parent", "#");
        List<GlSysFunc> glSysFuncs = baseMapper.selectByExample(glSysFunc, example);

        return glSysFuncs;
    }

    /*
     * 查询所有的顶层目录，自动加载子层菜单
     * @return 所有的目录
     */
    @Override
    public List<GlSysFunc> selectGlSysFuncsWithChildren() {

        GlSysFunc glSysFunc = new GlSysFunc();
        Example example = new Example();
        example.createCriteria().andFieldEqualTo("enable", true).andFieldEqualTo("isLeaf", false);
        List<GlSysFunc> glSysFuncs = baseMapper.selectByExample(glSysFunc, example);

        //拼装树形json字符串
        glSysFuncs = new TreeBuilder<GlSysFunc>().buildTree(glSysFuncs);
        return glSysFuncs;
    }

    @Override
    public List<GlSysFunc> selectWithPriv(List<String> privs) {
        List<GlSysFunc> glSysPrivs = glSysFuncMapper.selectWithPriv(privs);

        //拼装树形json字符串
        glSysPrivs = new TreeBuilder<GlSysFunc>().buildTree(glSysPrivs);

        return glSysPrivs;
    }

}
