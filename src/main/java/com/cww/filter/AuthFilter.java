package com.cww.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * Created by user on 2018/11/9.
 */
public class AuthFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;//post error route static;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        String routeHost = RequestContext.getCurrentContext().getRequest().getRemoteHost();
        return !routeHost.contains("127.16.");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx  =RequestContext.getCurrentContext();
        String userName = ctx.getRequest().getParameter("username");
        if(StringUtils.isEmpty(userName)){
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(401);// 返回错误码
            ctx.setResponseBody("{\"result\":\"username is not correct!\"}");// 返回错误内容
            ctx.set("isSuccess", false);

            return null;
        }

        return null;
    }
}
