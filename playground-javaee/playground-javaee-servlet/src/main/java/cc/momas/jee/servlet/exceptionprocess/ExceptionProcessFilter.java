package cc.momas.jee.servlet.exceptionprocess;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * 统一处理异常的Filter
 *
 * @author Sod-Momas
 * @since 2021.06.13
 */
@WebFilter(value = {"/exception"})
public class ExceptionProcessFilter implements Filter {

    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("ExceptionProcessFilter 已初始化");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            chain.doFilter(request, response);
        } catch (Throwable throwable) {
            logger.severe(throwable.getMessage());
            ErrorCode errorCode;
            if (throwable instanceof BusinessException) {
                errorCode = ((BusinessException) throwable).getErrorCode();
            } else {
                // 默认为服务不可用, 当发生不可预料的异常 例如数据库出错 的异常时走这个逻辑，不暴露具体异常
                errorCode = ErrorCode.SERVER_ERROR;
            }
            String res = toJson(errorCode, throwable.getLocalizedMessage());
            response.getWriter().write(res);
        }
    }


    @Override
    public void destroy() {
        logger.info("统一异常过滤器已初销毁");
    }

    private String toJson(ErrorCode errorCode, String data) {
        // 格式化成 json
        return "{\"code\":\"" + errorCode.errorCode + "\",\"msg\":\"" + errorCode.errorMessage + "\",\"data\":\"" + data + "\"}";
    }
}
