package com.grandland.qdhx.web.security.header;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grandland.qdhx.core.bean.ResponseDate;
import com.grandland.qdhx.core.bean.ResponseDateCode;

/**
 * 登录失败的处理逻辑
 * @author loverqi
 * @date 2018年1月13日
 */
@Component
public class JsonAuthenticationFailureHandler implements AuthenticationFailureHandler {
    
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        
        PrintWriter out = response.getWriter();

        ResponseDate<String> responseDate = new ResponseDate<String>();
        responseDate.setCode(ResponseDateCode.USERNAME_OR_PASSWORD_ERROR);
        responseDate.setMessage(ResponseDateCode.USERNAME_OR_PASSWORD_ERROR_MESSAGE);
        out.write(objectMapper.writeValueAsString(responseDate));

        out.flush();
        out.close();
    }

}