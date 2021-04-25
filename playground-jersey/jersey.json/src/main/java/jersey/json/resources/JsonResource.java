package jersey.json.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jersey.json.response.HttpResponse;

/**
 * 这个类用来返回 json 数据,
 * 
 * @author 陈伟明
 *
 */
@Path("json")
public class JsonResource {

	// http://localhost:8080/webapi/json/1233
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public HttpResponse get(@PathParam("id") String id) {
		HttpResponse resp = new HttpResponse();
		resp.msg = "您的id是 : " + id;
		return resp;
	}
}
