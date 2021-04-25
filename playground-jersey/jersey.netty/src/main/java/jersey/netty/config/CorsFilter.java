package jersey.netty.config;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 跨域过滤器
 * 
 * @author sothe
 *
 */
public class CorsFilter implements ContainerResponseFilter {

	private static final Logger log = LoggerFactory.getLogger(CorsFilter.class);
	
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		log.debug("跨域异常处理");
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		/**
		 * 允许的Header值，不支持通配符
		 */
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
		/**
		 * 即使只用其中几种，header和options是不能删除的，因为浏览器通过options请求来获取服务的跨域策略
		 */
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		/**
		 * CORS策略的缓存时间
		 */
		responseContext.getHeaders().add("Access-Control-Max-Age", "1209600");

	}

}
