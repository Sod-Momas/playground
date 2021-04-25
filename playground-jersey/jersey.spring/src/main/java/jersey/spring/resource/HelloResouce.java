package jersey.spring.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import jersey.spring.response.HttpResponse;

@Path("hello")
public class HelloResouce {

	@Autowired
	private HttpResponse response;
	
	// http://localhost:8081/webapi/hello/hash
	@Path("hash")
	@GET
	// @Produces(MediaType.APPLICATION_JSON) // 测试转json,如果没有转成json 说明缺少jackson依赖
	@Produces(MediaType.TEXT_PLAIN)
	public String get() {
		// 测试spring 自动注入,如果是null则spring 没有启动
		return String.valueOf(response);
	}
	
}
