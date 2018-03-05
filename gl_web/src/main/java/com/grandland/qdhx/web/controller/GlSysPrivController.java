package com.grandland.qdhx.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grandland.qdhx.core.bean.ResponseDate;
import com.grandland.qdhx.core.bean.ResponseDateCode;
import com.grandland.qdhx.core.domain.Permissions;
import com.grandland.qdhx.web.service.GlSysPrivService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 权限管理的Controller， 所有的返回格式均为json
 * @author loverqi
 * @date 2018年2月6日
 */
@RestController
@RequestMapping("priv")
@Api(value = "权限管理", tags = "priv")
public class GlSysPrivController {
    @Autowired
    private GlSysPrivService glSysPrivService;

    /**
     * 获取所有的权限情况
     * @return ResponseDate<List<GlSysFunc>>
     */
    @ApiOperation(value = "获取所有的权限列表", notes = "获取所有的权限列表，code为0为成功")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId", value = "岗位的id", required = false, dataType = "int", paramType = "query"), })
    @RequestMapping(value = "/getPrivs.do", method = { RequestMethod.POST })
    public ResponseDate<List<Permissions>> getPrivs(Integer postId) {
        ResponseDate<List<Permissions>> responseDate = new ResponseDate<List<Permissions>>();

        List<Permissions> privs = glSysPrivService.selectGlSysFuncsWithPrivs(postId);

        //将数据放入返回值
        responseDate.setData(privs);

        return responseDate;
    }

    /**
     * 根据岗位id获取该岗位得到的授权
     * @return ResponseDate<List<GlSysFunc>>
     */
    @ApiOperation(value = "根据岗位id获取该岗位得到的授权", notes = "根据岗位id获取该岗位得到的授权，code为0为成功")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId", value = "岗位的id", required = true, dataType = "int", paramType = "query"), })
    @RequestMapping(value = "/getPrivsByPostId.do", method = { RequestMethod.POST })
    public ResponseDate<List<Permissions>> getPrivsByPostId(Integer postId) {
        ResponseDate<List<Permissions>> responseDate = new ResponseDate<List<Permissions>>();
        List<Permissions> privs = null;
        if (postId != null) {
            privs = glSysPrivService.selectPrivsByPostId(postId);
        } else {
            responseDate.setCode(ResponseDateCode.PARAMETER_ANOMALY);
            responseDate.setMessage(ResponseDateCode.PARAMETER_ANOMALY_MESSAGE);
        }

        //将数据放入返回值
        responseDate.setData(privs);

        return responseDate;
    }

}
