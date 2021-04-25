package jersey.spring.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import jersey.spring.response.HttpResponse;

/**
 * RESTful资源,也就是Controller
 * @author sothe
 *
 */
@Controller
@Path("hello")
public class HelloResouce {

	@Autowired
	private HttpResponse response;
	
	// http://localhost/webapi/hello/hash
	@Path("hash")
	@GET
	@Produces(MediaType.APPLICATION_JSON) // 测试转json,如果没有转成json 说明缺少jackson依赖
	public HttpResponse get() {
		// 测试spring 自动注入,如果是null则spring 没有启动
		return response;
	}
	
}
