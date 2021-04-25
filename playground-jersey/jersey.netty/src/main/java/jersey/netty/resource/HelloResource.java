package jersey.netty.resource;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import jersey.netty.response.HttpResponse;

@Path("hello")
public class HelloResource {

	private static final Logger log = LoggerFactory.getLogger(HelloResource.class);
	
	@Autowired
	private HttpResponse resp;

	// 测试字符串
	@Path("/")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "hello";
	}
	
	// 测试json序列化
	@Path("resp")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HttpResponse resp() {
		log.debug("测试成功");
		return HttpResponse.success();
	}
	
	// 测试spring
	@Path("ioc")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HttpResponse ioc() {
		return resp;
	}

}
