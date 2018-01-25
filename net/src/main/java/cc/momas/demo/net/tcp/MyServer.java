package cc.momas.demo.net.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket服务器
 * 
 * @author sod
 *
 */
public class MyServer {

	/**
	 * 开启服务器
	 * 
	 * @param serverSocket
	 *            监听服务器的socket服务
	 */
	public void startServer(ServerSocket serverSocket) {

		// socket接口,用于接收socket的所有处理
		Socket socket = null;
		// 接收从客户端发过来的流数据
		InputStream is = null;
		// 用于发送内容到客户端
		OutputStream os = null;
		// 缓冲区
		BufferedReader br = null;

		System.out.println("服务器启动成功");

		try {
			do {
				// 开始接收请求,这一步会阻塞程序
				socket = serverSocket.accept();

				System.out.println("服务器开始接收请求内容");

				// 获取请求中的输入流
				is = socket.getInputStream();
				// 把字节流转换到字符流
				br = new BufferedReader(new InputStreamReader(is));
				String data = null;
				while ((data = br.readLine()) != null) {
					data = new String(data.getBytes("UTF-8"));
					System.out.println("客户端数据:" + data);
				}
				// 使服务器一直运行
			} while (true);

		} catch (IOException e) {
			System.err.println("服务器输入/输出时出错!");
			e.printStackTrace();
		} finally {
			// 清理资源
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException ignore) {
			}
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// 监听本地的端口
		int serverPort = 8000;

		System.out.println("服务器正在开启");

		// 服务端socket
		try {

			new MyServer().startServer(new ServerSocket(serverPort));

		} catch (IOException e) {
			System.err.println("服务器开启失败,参考原因:" + e.getMessage());
		}
	}

}
