package cc.momas.java.demo.web.action;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String Name;
	private int Age;
	private String Result;

	@Override
	public String execute() throws Exception {
		this.setResult("name:" + Name);
		this.setResult(this.getResult() + "&age:" + String.valueOf(Age));
		return "success";
	}

	// ------------getter   and   setter  --------------
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

}
