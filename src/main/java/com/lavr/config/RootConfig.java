package com.lavr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by lavr on 5/27/15.
 * config file for beans, etc.
 */

@Configuration
@ComponentScan(basePackages={"com.lavr"},
        excludeFilters={
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value=EnableWebMvc.class)
        })
public class RootConfig {

}
