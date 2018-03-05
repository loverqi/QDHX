package com.grandland.qdhx.web.security.header;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grandland.qdhx.core.bean.ResponseDate;

/**
 * 退出登录成功的处理逻辑
 * @author loverqi
 * @date 2018年1月13日
 */
@Component
public class JsonLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        ResponseDate<String> responseDate = new ResponseDate<String>();
        out.write(objectMapper.writeValueAsString(responseDate));

        out.flush();
        out.close();
    }

}