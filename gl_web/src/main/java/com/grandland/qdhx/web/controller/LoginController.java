package com.grandland.qdhx.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.grandland.qdhx.core.bean.ResponseDate;
import com.grandland.qdhx.core.bean.ResponseDateCode;
import com.grandland.qdhx.core.domain.GlSysUser;
import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.web.security.util.SecurityUtil;
import com.grandland.qdhx.web.service.GlSysUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 与用户登录有关的类，大部分的方法已通过集成spring security开发，仅仅是给前端展示接口使用不实际开发
 * @author loverqi
 * @date 2018年1月12日
 */
@RestController
@RequestMapping("login")
@Api(value = "登录相关", tags = "login")
public class LoginController {

    @Autowired
    private GlSysUserService glSysUserService;

    /** 
     * 获取已登录用户信息
     * @return ResponseDate<String>
     */
    @ApiOperation(value = "获取已登录用户信息", notes = "获取已登录用户信息，code为0是成功")
    @RequestMapping(value = "/getLoginUser.do", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseDate<GlSysUser> getLoginUsername() {
        ResponseDate<GlSysUser> responseDate = new ResponseDate<GlSysUser>();

        GlSysUser glSysUser = new GlSysUser();
        Example example = new Example();
        example.createCriteria().andFieldEqualTo("username", SecurityUtil.getUsername()).andFieldEqualTo("enable",
                true);
        List<GlSysUser> glSysUsers = glSysUserService.selectByExampleAndConnectionName(glSysUser, example);

        if (glSysUsers != null && glSysUsers.size() > 0) {
            responseDate.setData(glSysUsers.get(0));
        } else {
            //未找到用户
            responseDate.setCode(ResponseDateCode.NOT_FIND_USER_ERROR);
            responseDate.setMessage(ResponseDateCode.USERNAME_OR_PASSWORD_ERROR_MESSAGE);
        }

        return responseDate;
    }

    /**
     * 用户未登录的返回json信息
     */
    @ApiIgnore()
    @RequestMapping(value = "/notLoginAjax.do", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseDate<String> notLoginAjax() {

        ResponseDate<String> responseDate = new ResponseDate<String>();

        responseDate.setCode(ResponseDateCode.NOT_LOGIN);
        responseDate.setMessage(ResponseDateCode.NOT_LOGIN_MESSAGE);

        return responseDate;
    }

    /**
     * 判断是否是ajax请求
     * @param request
     * @param response
     * @return
     */
    @ApiIgnore()
    @RequestMapping(value = "/notLogin.do", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView notLogin(HttpServletRequest request, HttpServletResponse response) {

        String ajaxHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(ajaxHeader);
        if (isAjax) {
            return new ModelAndView("forward:/login/notLoginAjax.do");
        } else {
            return new ModelAndView("redirect:/views/login.do");
        }

    }

    /** 
     * 申请用户登录
     * @return ResponseDate<String>
     */
    @ApiOperation(value = "申请用户登录", notes = "用户登录的申请，code为0是登录成功")
    @RequestMapping(value = "/userLogin.do", method = { RequestMethod.POST })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"), })

    public ResponseDate<String> userLogin(String username, String password) {
        return null;
    }

    /** 
     * 申请用户退出登录
     * @return ResponseDate<String>
     */
    @ApiOperation(value = "申请用户退出登录", notes = "用户退出登录的申请，code为0是成功")
    @RequestMapping(value = "/userUnlogin.do", method = { RequestMethod.GET })
    public ResponseDate<String> userUnlogin() {
        return null;
    }

}
