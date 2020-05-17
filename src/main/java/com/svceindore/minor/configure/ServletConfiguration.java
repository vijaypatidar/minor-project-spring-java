package com.svceindore.minor.configure;

import com.svceindore.minor.servlet.HomeServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServlet;

@Configuration
public class ServletConfiguration {
    @Bean
    public ServletRegistrationBean<HttpServlet> countryServlet() {
        ServletRegistrationBean<HttpServlet> servRegBean = new ServletRegistrationBean<>();
        // pass the object of servlet
        servRegBean.setServlet(new HomeServlet());
        // define path pattern for this servlet
        servRegBean.addUrlMappings("/otp");
        servRegBean.setLoadOnStartup(1);
        return servRegBean;
    }
}
