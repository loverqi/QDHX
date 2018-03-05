package com.grandland.qdhx.web.security.header;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grandland.qdhx.core.bean.ResponseDate;
import com.grandland.qdhx.core.bean.ResponseDateCode;

/**
 * 权限验证失败的返回类
 * @author loverqi
 * @date 2018年1月14日
 */
@Component
public class JsonAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        ResponseDate<String> responseDate = new ResponseDate<String>();
        responseDate.setCode(ResponseDateCode.LACK_AUTHORITY);
        responseDate.setMessage(ResponseDateCode.LACK_AUTHORITY_MESSAGE);
        out.write(objectMapper.writeValueAsString(responseDate));

        out.flush();
        out.close();

    }

}
