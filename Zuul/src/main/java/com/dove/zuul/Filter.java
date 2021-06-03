package com.dove.zuul;

import com.alibaba.fastjson.JSONObject;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;

@Component
public class Filter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(Filter.class);

    private  JSONObject result = new JSONObject(){
        {
            put("status", 200);
            put("message", "success");
        }
    };
    /**
     * filterType：返回一个字符串代表过滤器的类型
     * 在zuul中定义了四种不同生命周期的过滤器类型，具体如下：

     pre：路由之前
     routing：路由之时
     post： 路由之后
     error：发送错误调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder：过滤的顺序
     filterOrder：过滤的优先级，数字越大，优先级越低
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    //shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //run：过滤器的具体逻辑，这里只是将请求的URL简单写到日志中
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String s = String.format("%s>>>>>>>%s",request.getMethod(),request.getRequestURL().toString());
        log.info(s);
        String ipaddress = request.getRemoteHost();
        if (ipaddress.equals("127.0.0.1")) {
            result.put("status", 300);
            result.put("message", "fail");
            return result;
        }
        return null;
    }
}
