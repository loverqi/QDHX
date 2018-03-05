package com.grandland.qdhx.webservice.config;


import com.grandland.qdhx.webservice.service.InspectWarehouseService;
import com.grandland.qdhx.webservice.service.MessageViewService;
import com.grandland.qdhx.webservice.service.impl.InspectWarehourceServiceImpl;
import com.grandland.qdhx.webservice.service.impl.MessageViewServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.xml.ws.Endpoint;


@Configuration
public class CXFConfig {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new CXFServlet(), "/webservice/*");
        bean.setLoadOnStartup(0);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }


    @Bean
    public MessageViewService messageViewService() {
        return new MessageViewServiceImpl();
    }


    @Bean
    public Endpoint endpointMessageView() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), messageViewService());
        endpoint.publish("/massage_view");
        return endpoint;
    }


    @Bean
    public InspectWarehouseService inspectWarehouseService() {
        return new InspectWarehourceServiceImpl();
    }


    @Bean
    public Endpoint endpointInspection() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), inspectWarehouseService());
        endpoint.publish("/inspection");
        return endpoint;
    }
}
