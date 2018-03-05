package com.grandland.qdhx.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grandland.qdhx.core.bean.ResponsePageDate;
import com.grandland.qdhx.core.domain.GlSysPost;
import com.grandland.qdhx.core.mapper.GlSysDeptMapper;
import com.grandland.qdhx.core.mapper.GlSysPostMapper;
import com.grandland.qdhx.core.mapper.GlSysPrivMapper;
import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.web.service.GlSysPostService;
import com.grandland.qdhx.web.service.base.BaseServiceImpl;

/**
 * 岗位管理的Servlet类
 * @author loverqi
 * @date 2018年1月5日
 */
@Service
public class GlSysPostServiceImpl extends BaseServiceImpl<GlSysPost> implements GlSysPostService {

    @Autowired
    private GlSysPostMapper glSysPostMapper;

    @Autowired
    private GlSysDeptMapper glSysDeptMapper;

    @Autowired
    private GlSysPrivMapper glSysPrivMapper;

    /**
     * 查询简化后的岗位信息
     * @return
     */
    @Override
    public List<Map<String, String>> selectSimplifyPosts(Integer deptId) {
        List<Map<String, String>> simplifyPosts = glSysPostMapper.selectSimplifyPosts(deptId);

        return simplifyPosts;
    }

    @Override
    public ResponsePageDate<GlSysPost> selectByExampleWithRowboundsAndDeptName(GlSysPost glSysPost, Example example,
            int page, int pageSize) {
        ResponsePageDate<GlSysPost> pageDate = super.selectByExampleWithRowbounds(glSysPost, example, page, pageSize);

        List<GlSysPost> glSysPosts = pageDate.getList();

        //填充外键名字字段
        for (GlSysPost glSysPostTemp : glSysPosts) {
            Integer deptId = glSysPostTemp.getDeptId();
            if (deptId != null) {
                Map<String, String> maps = glSysDeptMapper.selectDeptNameById(deptId);
                if (maps != null && maps.size() > 0) {
                    String deptName = maps.get("deptName");
                    glSysPostTemp.setFieldValueByKey("deptName", deptName);
                }

                List<String> privs = glSysPrivMapper.selectPrivTextByPostId(glSysPostTemp.getId());
                glSysPostTemp.setPrivs(privs);
            }
        }

        return pageDate;
    }

}
