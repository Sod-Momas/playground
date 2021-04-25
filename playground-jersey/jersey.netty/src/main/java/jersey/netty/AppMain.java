package jersey.netty;

import java.net.URI;
import java.util.Properties;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.Channel;
import jersey.netty.config.AppConfig;

public class AppMain extends ResourceConfig {
	private static final Logger log = LoggerFactory.getLogger(AppMain.class);

	public static void main(String[] args) {

		Properties props = readArgs(args); // 读取参数
		int port = Integer.parseInt(props.getProperty("httpPort", "80")); // 默认监听80端口
		String resourcePackage = "jersey.netty.resource";             // 放资源的包名
		String host = "http://localhost/";                            // 启动的url主机

		Channel server = null;
		try {
			ResourceConfig resConfig = new AppConfig(resourcePackage); // 配置资源包名
			URI baseUri = UriBuilder.fromUri(host).port(port).build(); // 服务器配置 
			server = NettyHttpContainerProvider.createServer(baseUri, resConfig, false);
			Thread.currentThread().join();// 阻塞线程,使服务器不退出
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		} finally {
			log.error("服务器异常关闭");
			if (server != null) {
				server.close();
			}
		}

	}
	

	/**
	 * 读取列表,参数需要符合以下两种格式之一:
	 * <ul>
	 * <li><code>{"--arg1=value1","--value"}</code></li>
	 * </ul>
	 * 
	 * @param args 参数列表
	 * @return 参数键值对
	 */
	private static Properties readArgs(String[] args) {
		Properties props = new Properties();

		if (args == null || args.length == 0) {
			return props;
		}

		final String prefix = "--";
		final int prefixLen = 2; // prefix.length();

		for (String p : args) {
			if (p.startsWith(prefix)) {
				if (p.contains("=")) {
					// 具名参数 形式如 --key=value
					final int index = p.indexOf('=');
					String key = p.substring(prefixLen, index); // 避开开头减号 --
					String value = p.substring(index + 1); // 避开中间等号 =
					props.put(key, value);
				} else {
					// 不具名参数 形式如 --key
					String arg = p.substring(prefixLen, p.length());
					props.put(arg, arg);
				}
			}
		}
		return props;
	}

}
