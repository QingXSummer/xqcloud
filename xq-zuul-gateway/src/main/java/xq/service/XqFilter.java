package xq.service;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 描述    :
 * Author :Qing_X
 * Date   :2019-03-24 20:06
 */
@Component
public class XqFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(XqFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String token = request.getParameter("token");
        if(token ==null){
            logger.error("token is empty");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(400);
            try {
                context.getResponse().getWriter().write("token is empty");
            } catch (IOException e) {
                logger.error("网关响应异常", e);
            }
        }
        return null;
    }
}
