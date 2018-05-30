package cc.momas.java.demo.generic.removeclzz;

import org.junit.Test;

import java.io.*;
import java.util.*;

public class TestMain {

	/**
	 * 测试序列化方法是否成功
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Test
	public void test3() throws IOException, ClassNotFoundException {
		Topic topic = new Topic();
		topic.imgUrl = "fuck you";
		topic.password = "password";
		System.out.println(topic);
		topic = transfer(topic);
		System.out.println(topic);
	}

	/**
	 * 测试泛型在运行时类型是否一致
	 */
	@Test
	public void test2() {
		Class a1 = new ArrayList<Integer>().getClass();
		Class a2 = new ArrayList<String>().getClass();
		System.out.println(a1 == a2);
	}

	/**
	 * 测试泛型序列化后是否可以还原类型
	 */
	@Test
	public void test() throws IOException, ClassNotFoundException {

		List<Object> list = new ArrayList<>(20);

		list.addAll(getTopicList());
		list.addAll(getHotwordList());
		foreachList(list);
		System.out.println("================================");
		list = transfer(list);
		System.out.println("================================");
		foreachList(list);
	}

	/**
	 * 测试字段里的List是否可以被还原
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Test
	public void test4() throws IOException, ClassNotFoundException {
		Module module = new Module();
		module.id = 111;
		module.name = "test";
		module.list = new ArrayList<>(20);
		module.list.addAll(getHotwordList());
		module.list.addAll(getTopicList());
		System.out.println(module);
		module = transfer(module);
		System.out.println(module);

	}

	private void foreachList(List list){
		for (Object obj : list) {
			System.out.println(obj);
			if(obj instanceof List){
				foreachList((List) obj);
			}
		}
	}

	/**
	 * 读进内存序列化再反序列化出来
	 * @param serializable
	 * @param <Serializable>
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private <Serializable> Serializable transfer(Serializable serializable) throws IOException, ClassNotFoundException {

		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(buf);

		oos.writeObject(serializable);

		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
		serializable = (Serializable) ois.readObject();

		return serializable;
	}

	private List<Topic> getTopicList() {
		List<Topic> list = new ArrayList<>(10);
		for (int i = 0; i < 10; i++) {
			list.add(generateTopic());
		}
		return list;
	}

	private List<Hotword> getHotwordList() {
		List<Hotword> list = new ArrayList<>(10);
		for (int i = 0; i < 10; i++) {
			list.add(generateHotword());
		}
		return list;
	}

	private Topic generateTopic() {
		Topic topic = new Topic();
		topic.createTime = new Date();
		topic.imgUrl = UUID.randomUUID().toString();
		topic.refId = (int) (Math.random() * 100);
		topic.moduleId = (int) (Math.random() * 100);
		return topic;
	}

	private Hotword generateHotword() {
		Hotword hotword = new Hotword();
		hotword.createTime = new Date();
		hotword.moduleId = (int) (Math.random() * 100);
		hotword.title = UUID.randomUUID().toString();
		return hotword;
	}

}