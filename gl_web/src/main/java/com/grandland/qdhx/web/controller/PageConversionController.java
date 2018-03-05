package com.grandland.qdhx.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.grandland.qdhx.core.domain.GlSysFunc;
import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.web.service.GlSysFuncService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 前端获取页面的的Controller，使用到了ModelAndView，非前后端分离体系
 * @author loverqi
 * @date 2018年1月9日
 */
@Controller
@RequestMapping("views")
@Api(value = "获取动态页面", tags = { "page" })
public class PageConversionController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private GlSysFuncService glSysFuncService;

    @ApiOperation(value = "获取动态jsp页面", tags = { "动态页面" }, notes = "该方法直接返回页面")
    @RequestMapping(value = { "/{pageName}.do", "/**/{pageName}.do" }, method = { RequestMethod.GET })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageName", value = "页面名称", required = true, dataType = "String", paramType = "path"), })
    public ModelAndView menu(@PathVariable("pageName") String pageName) {

        //找到当前的menu菜单
        GlSysFunc glSysFunc = new GlSysFunc();
        Example example = new Example();
        example.createCriteria().andFieldEqualTo("enable", true).andFieldEqualTo("is_leaf", false).andFieldLike("path",
                "%" + pageName + ".do");
        List<GlSysFunc> paFuncs = glSysFuncService.selectByExample(glSysFunc, example);
        GlSysFunc thisMenu = (paFuncs != null && paFuncs.size() > 0) ? paFuncs.get(0) : new GlSysFunc();

        //获取当前的view
        String servletPath = request.getServletPath();

        return new ModelAndView(servletPath.substring("/views/".length(), servletPath.length() - 3), "thisMenu",
                thisMenu.getId());
    }

}
