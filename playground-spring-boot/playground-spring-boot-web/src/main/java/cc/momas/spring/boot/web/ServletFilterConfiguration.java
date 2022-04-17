package cc.momas.spring.boot.web;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Sod-Momas
 * @since 2022/4/17
 */
@Configuration
// 用这个注解来给出控制权，当配置 spring.servlet.filter.enabled = false 时，该配置就不会启动，不配置默认是启动
@ConditionalOnProperty(prefix = "spring.servlet.filter", value = "enabled", matchIfMissing = true)
public class ServletFilterConfiguration {

    @Bean
    public FilterRegistrationBean<ServletFilter> servletFilter() {
        // 包装为spring过滤器
        FilterRegistrationBean<ServletFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new ServletFilter());
        registration.addUrlPatterns("/*");
        registration.setName("servletFilter");
        registration.setOrder(1); // 设置过滤器被调用的顺序
        return registration;
    }

    public static class ServletFilter implements javax.servlet.Filter {
        private final Logger log = Logger.getLogger(getClass().getName());

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            log.info("servlet filter init.");
            Filter.super.init(filterConfig);
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            if (request.getParameter("hello") != null) {
                // 检测到符合请求符合要求，例如包含token，把请求继续往业务里传
                // 当然这里也要把一些静态资源的请求放行
                log.info("servlet filter before filter.");
                chain.doFilter(request, response);
                log.info("servlet filter after filter.");
            } else {
                // 检测不到符合要求的逻辑，则可以这样返回一个错误提示，请求内容不会传到servlet层处理
                log.info("servlet filter detect haven't 'hello' parameter, forbidden.");
                response.setContentType("text/plain");
                response.getWriter().write("haven't 'hello' parameter,forbidden");
            }
        }

        @Override
        public void destroy() {
            log.info("servlet filter destroy.");
            Filter.super.destroy();
        }
    }

}
