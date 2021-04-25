package jersey.spring.response;

/**
 * 统一规范接口数据
 * 
 * @author sothereer@gmail.com
 *
 */
public class HttpResponse {

	/**
	 * 响应状态, 最好使用http 状态码标明,默认成功
	 */
	public int status = 200;
	/**
	 * 数据起始 偏移量,多用于分页标记这一页第一项的id或者下标
	 */
	public int start = 0;
	/**
	 * 数据量, 多用于分页,标记一页多少行数据
	 */
	public int count = 10;
	/**
	 * 数据,数据
	 */
	public Object data;
	/**
	 * 响应的消息,可以告诉前端发生了什么异常
	 */
	public String msg;

	// 这里可以添加更多的工厂方法,生产成功的响应与失败的响应
	
	public static HttpResponse fail() {
		HttpResponse resp = new HttpResponse();
		resp.status = 500;
		resp.data = "server error";
		resp.msg = "server error";
		return resp;
	}
}
