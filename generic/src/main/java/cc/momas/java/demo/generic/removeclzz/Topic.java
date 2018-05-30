package cc.momas.java.demo.generic.removeclzz;

import java.io.Serializable;
import java.util.Date;

public class Topic implements Serializable{
	public Integer id;
	public Integer moduleId;
	public String style;
	public String name;
	public String imgUrl;
	public Integer seq;
	public Date createTime;
	public Short refType;
	public Integer refId;
	public String refUrl;
	public transient String password;

	@Override
	public String toString() {
		return "Topic{" +
				"id=" + id +
				", moduleId=" + moduleId +
				", style='" + style + '\'' +
				", name='" + name + '\'' +
				", imgUrl='" + imgUrl + '\'' +
				", seq=" + seq +
				", createTime=" + createTime +
				", refType=" + refType +
				", refId=" + refId +
				", refUrl='" + refUrl + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
