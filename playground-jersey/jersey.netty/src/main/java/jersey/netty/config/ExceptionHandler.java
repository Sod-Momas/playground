package jersey.netty.config;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jersey.netty.response.HttpResponse;

/**
 * 统一异常处理类
 * 
 * @author sothereer@gmail.com
 *
 */
@Provider
public class ExceptionHandler implements ExceptionMapper<Exception>  {

	private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);
	
	@Override
	public Response toResponse(Exception exception) {
		// HttpResponse 是个自定义的类
		 //LogKit.error(exception.getMessage(), exception);
		log.debug("统一异常类");
        return Response.serverError().entity(HttpResponse.fail()).build();
	}

}
