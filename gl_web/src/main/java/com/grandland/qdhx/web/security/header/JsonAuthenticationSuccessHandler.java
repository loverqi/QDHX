package com.grandland.qdhx.web.security.header;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grandland.qdhx.core.bean.ResponseDate;
import com.grandland.qdhx.core.domain.GlSysUser;
import com.grandland.qdhx.core.mapper.GlSysUserMapper;
import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.web.security.util.SecurityUtil;

/**
 * 登录成功的处理逻辑
 * @author loverqi
 * @date 2018年1月13日
 */
@Component
public class JsonAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private GlSysUserMapper glSysUserMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        GlSysUser glSysUser = new GlSysUser();
        Example example = new Example();
        List<GlSysUser> users = glSysUserMapper.selectByExample(glSysUser, example);
        if (users != null && users.size() > 0) {
            glSysUser = users.get(0);
        }

        //将当前请求的用户放入session
        request.getSession().setAttribute("userInfo", glSysUser);

        ResponseDate<Map<String, String>> responseDate = new ResponseDate<Map<String, String>>();

        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("username", SecurityUtil.getUsername());
        hashMap.put("path", "/views/menu.do");

        responseDate.setData(hashMap);
        out.write(objectMapper.writeValueAsString(responseDate));

        out.flush();
        out.close();
    }

}