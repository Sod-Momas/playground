package jersey.spring.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.filter.CorsFilter;

public class AppConfig extends ResourceConfig {

	// 在构造方法里配置
	public AppConfig() {
		super();
		// 需要注入spring组件解析过滤器
		register(RequestContextFilter.class);
		// 加载resources包名
		packages("jersey.spring.resource");
		// 注册数据转换器
		register(JacksonFeature.class);
		// 注册日志
		register(LoggingFeature.class);
		// 异常处理
		register(ExceptionHandler.class);
		// 跨域过滤器注册
		register(CorsFilter.class);
	}
}
