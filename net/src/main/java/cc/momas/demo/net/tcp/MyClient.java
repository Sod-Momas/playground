package cc.momas.demo.net.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * socket 客户端
 * 
 * @author sod
 *
 */
public class MyClient {

	public static void main(String[] args) {

		OutputStream outputStream = null;
		Socket socket = null;

		try {

			socket = new Socket("localhost", 8000);

			System.out.println("客户端启动成功,客户端开始向服务器发送请求");

			// 获取socket输出流(向服务器输出)
			outputStream = socket.getOutputStream();

			// 向服务器输出内容,虽然这里使用的是循环,但输出的内容会拼接到一起,一次性发送
			for (int i = 0; i < 0xf; i++) {
				// 控制时间,方便观察
				Thread.sleep(500);
				outputStream.write("这是一条来自客户端的消息".getBytes("UTF-8"));
				System.out.println("输出一次");
			}
			// 清空输出缓存并输出
			outputStream.flush();
			outputStream.close();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 清理资源
			try {
				socket.close();
			} catch (IOException ignore) {
			}
		}
	}
}
