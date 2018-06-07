package com.excilys.cdb.restConfig;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.excilys.cdb.springConfig.ApplicationConfig;

public class ServiceWebConfig
        extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { ApplicationConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { ServiceConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected DispatcherServlet createDispatcherServlet(
            WebApplicationContext servletAppContext) {
        final DispatcherServlet servlet = (DispatcherServlet) super.createDispatcherServlet(
                servletAppContext);
        servlet.setThrowExceptionIfNoHandlerFound(true);
        return servlet;
    }
}