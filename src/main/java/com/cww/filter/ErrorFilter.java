package com.cww.filter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.ReflectionUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * Created by user on 2018/11/9.
 */
public class ErrorFilter extends ZuulFilter {
    public Object run() {
        try {
            RequestContext ex = RequestContext.getCurrentContext();
            HttpServletRequest request = ex.getRequest();
            request.setAttribute("errorMessage", ex.getThrowable().getMessage());
            ex.getThrowable().printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("zuulError");
            if(dispatcher != null) {
                ex.set("sendErrorFilter.ran", true);
                if(!ex.getResponse().isCommitted()) {
                    dispatcher.forward(request, ex.getResponse());
                }
            }
        } catch (Exception var5) {
            ReflectionUtils.rethrowRuntimeException(var5);
        }

        return null;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getThrowable() != null
                && !ctx.getBoolean("sendErrorFilter.ran", false);
    }

    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

}
