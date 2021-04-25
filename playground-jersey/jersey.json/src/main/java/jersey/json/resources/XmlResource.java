package jersey.json.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jersey.json.response.HttpResponse;
import jersey.json.response.XmlResponse;

/**
 * 这个类用来返回 xml 格式数据
 * 
 * @author 陈伟明
 *
 */
@Path("xml")
public class XmlResource {

	// http://localhost:8080/webapi/xml/1233
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public HttpResponse get(@PathParam("id") String id) {
		HttpResponse resp = new XmlResponse();
		resp.msg = "您的id是 : " + id;
		return resp;
	}
}
