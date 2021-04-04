package cc.momas.scn.gw.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Sod-Momas
 * @since 2021-01-30
 */
@Component
public class HiFilter extends ZuulFilter {
    private final static Logger log = LoggerFactory.getLogger(HiFilter.class);

    @Override
    public String filterType() {
//        pre：路由之前
//        routing：路由之时
//        post： 路由之后
//        error：发送错误调用
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
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        // 对请求进行校验，可以校验 token 等
        log.info("{} >>> {}", request.getMethod(), request.getRequestURL().toString());
        String name = request.getParameter("name");
        if (null == name) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(400);
            try {
                ctx.getResponse().getWriter().write("{\"msg\":\"'name' is not present\",\"error\":\"sorry, out of service\"}");
            } catch (IOException e) {
                throw new ZuulException(e, ErrorCodeEnum.GATEWAY_ERROR.code, ErrorCodeEnum.GATEWAY_ERROR.msg);
            }
//            这里启用抛异常的话可以让 zuul 进行异常数量统计，但需要再做个全局异常处理，不然前端会接收到spring 空白页
//            throw new ZuulException("'name'不存在", ErrorCodeEnum.GATEWAY_ERROR.code, ErrorCodeEnum.GATEWAY_ERROR.msg);
        }
        return null;
    }
}
