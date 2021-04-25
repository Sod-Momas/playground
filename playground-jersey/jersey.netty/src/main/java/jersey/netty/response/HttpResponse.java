package jersey.netty.response;

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
	private int status = 200;
	/**
	 * 数据起始 偏移量,多用于分页标记这一页第一项的id或者下标
	 */
	private int start = 0;
	/**
	 * 数据量, 多用于分页,标记一页多少行数据
	 */
	private int count = 0;
	/**
	 * 数据,数据
	 */
	private Object data;
	/**
	 * 响应的消息,可以告诉前端发生了什么异常
	 */
	private String msg;

	// 这里可以添加更多的工厂方法,生产成功的响应与失败的响应
	
	public static HttpResponse fail() {
		HttpResponse resp = new HttpResponse();
		resp.status = 500;
		resp.data = "server error";
		resp.msg = "server error";
		return resp;
	}

	public static HttpResponse success() {
		return new HttpResponse();
	}

	// getter and setter 
	
	public int getStatus() {
		return status;
	}

	public HttpResponse setStatus(int status) {
		this.status = status;
		return this;
	}

	public int getStart() {
		return start;
	}

	public HttpResponse setStart(int start) {
		this.start = start;
		return this;
	}

	public int getCount() {
		return count;
	}

	public HttpResponse setCount(int count) {
		this.count = count;
		return this;
	}

	public Object getData() {
		return data;
	}

	public HttpResponse setData(Object data) {
		this.data = data;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public HttpResponse setMsg(String msg) {
		this.msg = msg;
		return this;
	}
}
