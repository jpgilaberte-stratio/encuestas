package com.aft.encuestas.control.core;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;



public class FilterAOP implements Filter{

     static final Logger LOG = Logger.getLogger(FilterAOP.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.info("Start " + "todo");
        filterChain.doFilter(servletRequest,servletResponse);
        LOG.info("Finish " + "todo");

    }

    @Override
    public void destroy() {

    }
}
