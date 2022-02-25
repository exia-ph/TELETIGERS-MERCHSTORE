package com.merchstore.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class StoreWebInit extends AbstractAnnotationConfigDispatcherServletInitializer
{

    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class[] {StoreConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return null;
    }

    @Override
    protected String[] getServletMappings()
    {
        return new String[] {"/"};
    }
    
}
