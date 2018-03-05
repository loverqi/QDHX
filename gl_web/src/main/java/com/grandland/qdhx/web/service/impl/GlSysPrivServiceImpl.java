package com.grandland.qdhx.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grandland.qdhx.core.domain.GlSysPost;
import com.grandland.qdhx.core.domain.GlSysPriv;
import com.grandland.qdhx.core.domain.Permissions;
import com.grandland.qdhx.core.mapper.PermissionsMapper;
import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.treedata.TreeBuilder;
import com.grandland.qdhx.web.service.GlSysPrivService;
import com.grandland.qdhx.web.service.base.BaseServiceImpl;

/**
 * 权限管理的Servlet类
 * @author loverqi
 * @date 2018年2月6日
 */
@Service
public class GlSysPrivServiceImpl extends BaseServiceImpl<GlSysPriv> implements GlSysPrivService {

    @Autowired
    private PermissionsMapper permissionsMapper;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<Permissions> selectGlSysFuncsWithPrivs(Integer postId) {
        List<Permissions> permissionses = permissionsMapper.selectGlSysFuncsWithPrivs(postId);

        //拼装树形json字符串
        permissionses = new TreeBuilder().buildTree(permissionses);

        return permissionses;
    }

    @Override
    public List<Permissions> selectPrivsByPostId(Integer postId) {

        List<Permissions> permissionses = permissionsMapper.selectPrivsByPostId(postId);

        return permissionses;
    }

    @Override
    public void setPrivs(GlSysPost post, List<Integer> privIds) {
        List<GlSysPriv> updateGlSysPrivs = new ArrayList<GlSysPriv>();
        List<GlSysPriv> newGlSysPrivs = new ArrayList<GlSysPriv>();

        GlSysPriv glSysPriv = new GlSysPriv();
        Example example = new Example();
        example.createCriteria().andFieldEqualTo("postId", post.getId());
        List<GlSysPriv> glSysPrivs = selectByExample(glSysPriv, example);
        for (Integer privId : privIds) {
            glSysPriv = new GlSysPriv();
            glSysPriv.setPostId(post.getId());
            glSysPriv.setFuncId(privId);
            glSysPriv.setEnable(true);

            //添加到重新授权的列表
            if (glSysPrivs.contains(glSysPriv)) {
                glSysPrivs.remove(glSysPriv);
                updateGlSysPrivs.add(glSysPriv);
            } else {
                newGlSysPrivs.add(glSysPriv);
            }
        }

        //将已经授权的取消授权
        for (GlSysPriv glSysPrivTemp : glSysPrivs) {
            glSysPrivTemp.setEnable(false);
            updateGlSysPrivs.add(glSysPrivTemp);
        }

        insertSelective(newGlSysPrivs);
        updateByExampleSelective(updateGlSysPrivs);

    }

    @Override
    public int insertSelective(List<GlSysPriv> privs) {
        int count = 0;
        for (GlSysPriv glSysPriv : privs) {
            count += super.insertSelective(glSysPriv);
        }

        return count;
    }

    @Override
    public int updateByExampleSelective(List<GlSysPriv> privs) {
        int count = 0;
        for (GlSysPriv glSysPriv : privs) {
            Example example = new Example();
            example.createCriteria().andFieldEqualTo("postId", glSysPriv.getPostId()).andFieldEqualTo("funcId",
                    glSysPriv.getFuncId());
            count += super.updateByExampleSelective(glSysPriv, example);
        }

        return count;
    }
}
