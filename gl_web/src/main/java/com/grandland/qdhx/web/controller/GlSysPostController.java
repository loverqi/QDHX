package com.grandland.qdhx.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grandland.qdhx.core.bean.ResponseDate;
import com.grandland.qdhx.core.bean.ResponseDateCode;
import com.grandland.qdhx.core.bean.ResponsePageDate;
import com.grandland.qdhx.core.domain.GlSysPost;
import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.core.utils.StringUtil;
import com.grandland.qdhx.web.service.GlSysPostService;
import com.grandland.qdhx.web.service.GlSysPrivService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 部门操作有关的Controller， 所有的返回格式均为json
 * @author loverqi
 * @date 2018年1月8日
 */
@RestController
@RequestMapping("post")
@Api(value = "岗位管理", tags = "post")
public class GlSysPostController {

    @Autowired
    private GlSysPostService glSysPostService;
    @Autowired
    private GlSysPrivService glSysPrivService;

    @ApiOperation(value = "根据查询条件获取需要的岗位", notes = "根据查询条件获取需要的部门，code为0是成功")
    @RequestMapping(value = "/getPosts.do", method = { RequestMethod.POST })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptId", value = "部门编号", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "postName", value = "岗位名称", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "申请的页码", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页的个数", required = true, dataType = "int", paramType = "query"), })
    public ResponseDate<ResponsePageDate<GlSysPost>> getPosts(Integer deptId, String postName, Integer pageNum,
            Integer pageSize) {
        ResponseDate<ResponsePageDate<GlSysPost>> responseDate = new ResponseDate<ResponsePageDate<GlSysPost>>();
        if (pageNum == null || pageSize == null) {
            responseDate.setCode(ResponseDateCode.PARAMETER_ANOMALY);
            responseDate.setMessage(ResponseDateCode.PARAMETER_ANOMALY_MESSAGE);
        } else {
            Example example = new Example();

            if (deptId != null) {
                example.createCriteria().andFieldEqualTo("deptId", deptId);
            }
            if (StringUtil.isNotNull(postName)) {
                example.createCriteria().andFieldLike("postName", "%" + postName + "%");
            }

            GlSysPost glSysPost = new GlSysPost();
            ResponsePageDate<GlSysPost> glSysDepts = glSysPostService.selectByExampleWithRowboundsAndDeptName(glSysPost,
                    example, pageNum, pageSize);
            //将数据放入返回值
            responseDate.setData(glSysDepts);
        }

        return responseDate;
    }

    @ApiOperation(value = "查询所有的岗位简化信息列表", notes = "查询所有的岗位简化信息列表，code为0是成功")
    @RequestMapping(value = "/getSimplifyPosts.do", method = { RequestMethod.POST })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptId", value = "部门编号", required = false, dataType = "String", paramType = "query"), })
    public ResponseDate<List<Map<String, String>>> getSimplifyPosts(Integer deptId) {
        ResponseDate<List<Map<String, String>>> responseDate = new ResponseDate<List<Map<String, String>>>();

        if (deptId == null) {
            responseDate.setCode(ResponseDateCode.PARAMETER_ANOMALY);
            responseDate.setMessage(ResponseDateCode.PARAMETER_ANOMALY_MESSAGE);
        } else {

            List<Map<String, String>> simplifyPosts = glSysPostService.selectSimplifyPosts(deptId);

            //将数据放入返回值
            responseDate.setData(simplifyPosts);
        }

        return responseDate;
    }

    @ApiOperation(value = "新建或者修改岗位", notes = "新建或者修改岗位,有id时为修改，无id时为新建，code为0是成功")
    @RequestMapping(value = "/AddOrModifyPost.do", method = { RequestMethod.POST })
    public ResponseDate<Boolean> AddOrModifyPost(@RequestBody GlSysPost post) {

        ResponseDate<Boolean> responseDate = new ResponseDate<Boolean>();
        List<Integer> privIds = post.getPrivIds();
        int insert = 0;

        if (post.getId() == null && post.getDeptId() != null && post.getPostName() != null) {
            post.setEnable(true);
            insert = glSysPostService.insertSelective(post);
        } else if (post.getId() != null && (post.getDeptId() != null || post.getPostName() != null)) {
            insert = glSysPostService.updateByPrimaryKeySelective(post);
        } else {
            responseDate.setCode(ResponseDateCode.PARAMETER_ANOMALY);
            responseDate.setMessage(ResponseDateCode.PARAMETER_ANOMALY_MESSAGE);
        }

        // 进行岗位授权
        if (privIds != null) {
            glSysPrivService.setPrivs(post, privIds);
            responseDate.setCode(ResponseDateCode.SUCCESS);
            responseDate.setMessage(ResponseDateCode.SUCCESS_MESSAGE);
        }

        responseDate.setData(insert > 0);

        return responseDate;
    }

    @ApiOperation(value = "根据id删除岗位", notes = "根据id产出用户，code为0是成功")
    @RequestMapping(value = "/deletePost.do", method = { RequestMethod.POST })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "岗位的id", required = true, dataType = "int", paramType = "query"), })
    public ResponseDate<Boolean> deletePost(Integer id) {
        ResponseDate<Boolean> responseDate = new ResponseDate<Boolean>();
        if (id == null) {
            responseDate.setCode(ResponseDateCode.PARAMETER_ANOMALY);
            responseDate.setMessage(ResponseDateCode.PARAMETER_ANOMALY_MESSAGE);
        } else {
            GlSysPost glSysPost = new GlSysPost();
            glSysPost.setId(id);
            int deleteByPrimaryKey = glSysPostService.deleteByPrimaryKey(glSysPost);

            //将数据放入返回值
            responseDate.setData(deleteByPrimaryKey > 0);
        }

        return responseDate;
    }

    @ApiOperation(value = "根据id删除岗位", notes = "根据id删除部门，code为0是成功")
    @RequestMapping(value = "/deletePosts.do", method = { RequestMethod.POST })
    public ResponseDate<Boolean> deletePosts(@RequestBody List<Integer> ids) {
        ResponseDate<Boolean> responseDate = new ResponseDate<Boolean>();
        if (ids == null || ids.size() == 0) {
            responseDate.setCode(ResponseDateCode.PARAMETER_ANOMALY);
            responseDate.setMessage(ResponseDateCode.PARAMETER_ANOMALY_MESSAGE);
        } else {
            GlSysPost glSysPost = new GlSysPost();
            Example example = new Example();
            example.createCriteria().andFieldIn("id", ids);
            int deleteByPrimaryKey = glSysPostService.deleteByExample(glSysPost, example);

            //将数据放入返回值
            responseDate.setData(deleteByPrimaryKey > 0);
        }

        return responseDate;
    }

}
