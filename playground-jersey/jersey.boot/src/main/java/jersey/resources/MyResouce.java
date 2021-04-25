package jersey.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class MyResouce {

	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public String getIt() {
		return "got test!";
	}
	
	@Path("/inter")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String inter() {
		return "got inter";
	}
	
}
