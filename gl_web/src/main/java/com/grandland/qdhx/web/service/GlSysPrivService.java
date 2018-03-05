package com.grandland.qdhx.web.service;

import java.util.List;

import com.grandland.qdhx.core.domain.GlSysPost;
import com.grandland.qdhx.core.domain.GlSysPriv;
import com.grandland.qdhx.core.domain.Permissions;
import com.grandland.qdhx.web.service.base.BaseService;

/**
 * 权限管理的Servlet类
 * @author loverqi
 * @date 2018年2月6日
 */
public interface GlSysPrivService extends BaseService<GlSysPriv> {

    /**
     * 获取所有的权限列表
     * @return 所有的权限列表
     */
    List<Permissions> selectGlSysFuncsWithPrivs(Integer postId);

    /**
     * 获得指定岗位的权限列表
     * @param postId 岗位的id
     * @return 岗位的授权
     */
    List<Permissions> selectPrivsByPostId(Integer postId);

    /**
     * 给指定的岗位进行重新授权
     * @param post
     * @param privIds
     */
    void setPrivs(GlSysPost post, List<Integer> privIds);

    /**
     * 新增一个列表
     */
    int insertSelective(List<GlSysPriv> posts);

    /**
     * 修改一个列表
     */
    int updateByExampleSelective(List<GlSysPriv> posts);

}
