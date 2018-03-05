package com.grandland.qdhx.webservice;

import com.grandland.qdhx.core.config.QDHXCoreConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import(QDHXCoreConfig.class)
@SpringBootApplication
public class WebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class);
    }

}