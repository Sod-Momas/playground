package cc.momas.java.demo.generic.removeclzz;

import java.io.Serializable;
import java.util.Date;

public class Hotword implements Serializable {
	public Integer id;
	public Integer moduleId;
	public String title;
	public String fontColor;
	public String style;
	public Integer seq;
	public Date createTime;
	public Short refType;
	public Integer refId;
	public String refUrl;

	@Override
	public String toString() {
		return "Hotword{" +
				"id=" + id +
				", moduleId=" + moduleId +
				", title='" + title + '\'' +
				", fontColor='" + fontColor + '\'' +
				", style='" + style + '\'' +
				", seq=" + seq +
				", createTime=" + createTime +
				", refType=" + refType +
				", refId=" + refId +
				", refUrl='" + refUrl + '\'' +
				'}';
	}
}
