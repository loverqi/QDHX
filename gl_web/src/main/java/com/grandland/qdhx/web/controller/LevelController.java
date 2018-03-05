package com.grandland.qdhx.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grandland.qdhx.core.bean.ResponseDate;
import com.grandland.qdhx.core.domain.Level;
import com.grandland.qdhx.web.service.LevelService;

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
@RequestMapping("level")
@Api(value = "工作层级", tags = "level")
public class LevelController {

    @Autowired
    private LevelService levelService;

    @ApiOperation(value = "根据条件查询部门岗位层级菜单", notes = "根据条件查询部门岗位层级菜单，code为0是成功")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptName", value = "部门名称", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "postName", value = "岗位名称", required = false, dataType = "String", paramType = "query"), })
    @RequestMapping(value = "/getPostLevelNotUser.do", method = { RequestMethod.POST })
    public ResponseDate<List<Level>> getPostLevelNotUser(String deptName, String postName) {
        ResponseDate<List<Level>> responseDate = new ResponseDate<List<Level>>();

        List<Level> nodes = levelService.getPostLevelNotUser(deptName, postName);

        responseDate.setData(nodes);

        return responseDate;
    }

    @ApiOperation(value = "根据条件查询用户部门岗位层级菜单", notes = "根据条件查询用户部门岗位层级菜单，code为0是成功")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptName", value = "部门名称", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "postName", value = "岗位名称", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "realName", value = "用户真实姓名", required = false, dataType = "String", paramType = "query"), })
    @RequestMapping(value = "/getPostLevel.do", method = { RequestMethod.POST })
    public ResponseDate<List<Level>> getPostLevel(String deptName, String postName, String realName) {
        ResponseDate<List<Level>> responseDate = new ResponseDate<List<Level>>();

        List<Level> nodes = levelService.getPostLevel(deptName, postName, realName);

        responseDate.setData(nodes);

        return responseDate;
    }

}
