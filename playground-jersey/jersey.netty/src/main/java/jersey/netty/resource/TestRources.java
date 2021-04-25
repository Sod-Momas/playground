package jersey.netty.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jersey.netty.response.HttpResponse;

@Path("test")
public class TestRources {

	private static final Logger log = LoggerFactory.getLogger(TestRources.class);
	
	@Path("/")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "test";
	}

	// 测试统一异常处理
	@Path("err")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HttpResponse err() {
		log.error("进入异常");
		throw new RuntimeException("测试异常");
	}
}
