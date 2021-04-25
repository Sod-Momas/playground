package jersey.netty.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class AppConfig extends ResourceConfig {

	public AppConfig() {
		super();
	}

	public AppConfig(final String resourcePackage) {
		// 需要注入spring组件解析过滤器
		register(RequestContextFilter.class);
		// 加载resources包名
		packages(resourcePackage);
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
