package cc.momas.java.demo.entity;

public class Vip {
	// vip 等级
	private Integer level;
	// vip 称号
	private String nick;

	public Vip() {
	}

	public Vip(Integer level, String nick) {
		this.level = level;
		this.nick = nick;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
}
