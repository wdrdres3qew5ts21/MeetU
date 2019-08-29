/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meetu.gateway.filter;

import com.meetu.gateway.utils.FilterUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Test
 */
@Component
public class AuthenticationFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    private final int FILTER_ORDER = 1;

    private final boolean SHOULD_FILTER = true;

    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        System.out.println("fuq");
        log.info(String.format("%s RequetTo: %s RemoteIP: %s RemotePort: %s", request.getMethod(), request.getRequestURL().toString(), request.getRemoteAddr(), request.getRemotePort()));
        return null;
    }

}
