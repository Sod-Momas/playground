package cc.momas.java.demo.generic.removeclzz;

import java.io.Serializable;
import java.util.List;

public class Module implements Serializable{
	public String name;
	public int id;
	public List<Object> list;

	@Override
	public String toString() {
		return "Module{" +
				"name='" + name + '\'' +
				", id=" + id +
				", list=" + list +
				'}';
	}
}
