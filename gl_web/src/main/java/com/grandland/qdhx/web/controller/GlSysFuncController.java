package com.grandland.qdhx.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grandland.qdhx.core.bean.ResponseDate;
import com.grandland.qdhx.core.domain.GlSysFunc;
import com.grandland.qdhx.web.security.util.SecurityUtil;
import com.grandland.qdhx.web.service.GlSysFuncService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 菜单管理的Controller， 所有的返回格式均为json
 * @author loverqi
 * @date 2018年1月4日
 */
@RestController
@RequestMapping("menu")
@Api(value = "菜单管理", tags = "menu")
public class GlSysFuncController {
    @Autowired
    private GlSysFuncService glSysFuncService;

    /**
     * 获取所有的顶层菜单
     * @return ResponseDate<List<GlSysFunc>>
     */
    @ApiOperation(value = "获取所有的顶层菜单", notes = "该接口只返回顶层菜单，不会加载子菜单")
    @RequestMapping(value = "/getTopMenu.do", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseDate<List<GlSysFunc>> getTopMenu() {
        ResponseDate<List<GlSysFunc>> responseDate = new ResponseDate<List<GlSysFunc>>();
        List<GlSysFunc> glSysFuncs = glSysFuncService.selectGlSysFuncs();

        //将数据放入返回值
        responseDate.setData(glSysFuncs);

        return responseDate;
    }

    /**
     * 获取指定id的下一级菜单
     * @return ResponseDate<List<GlSysFunc>>
     */
    @ApiOperation(value = "获取指定id的下一级菜单", notes = "该接口只返回查询到菜单，不会加载子菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "父级菜单的id", required = true, dataType = "String", paramType = "query"), })
    @RequestMapping(value = "/getTheMenu.do", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseDate<List<GlSysFunc>> getTheMenu(String parentId) {
        ResponseDate<List<GlSysFunc>> responseDate = new ResponseDate<List<GlSysFunc>>();
        List<GlSysFunc> glSysFuncs = glSysFuncService.selectByParentByPrimaryKey(parentId);

        //将数据放入返回值
        responseDate.setData(glSysFuncs);

        return responseDate;
    }

    /**
     * 获取所有的菜单
     * @return ResponseDate<List<GlSysFunc>>
     */
    @ApiOperation(value = "获取所有的菜单", notes = "该接口只返回所有菜单，包括子菜单")
    @RequestMapping(value = "/getAllMenu.do", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseDate<List<GlSysFunc>> getAllMenu() {
        ResponseDate<List<GlSysFunc>> responseDate = new ResponseDate<List<GlSysFunc>>();

        List<GlSysFunc> glSysFuncs = glSysFuncService.selectWithPriv(SecurityUtil.getUserAuthorities());

        //将数据放入返回值
        responseDate.setData(glSysFuncs);

        return responseDate;
    }

}
