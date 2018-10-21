package cc.momas.java.demo.web.action;


public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String name;
	private int age;
	private String result;

	@Override
	public String execute() throws Exception {
		this.setResult("您的名字是:" + name + ",您的年龄是： " + getAge());
		return "success";
	}

	// ------------getter   and   setter  --------------
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
