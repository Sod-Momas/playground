package jersey.spring.config;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import jersey.spring.response.HttpResponse;

/**
 * 统一异常处理类
 * 
 * @author sothereer@gmail.com
 *
 */
@Provider
public class ExceptionHandler implements ExceptionMapper<Exception>  {

	@Override
	public Response toResponse(Exception exception) {
		// HttpResponse 是个自定义的类
		 //LogKit.error(exception.getMessage(), exception);
        return Response.serverError().entity(HttpResponse.fail()).build();
	}

}
