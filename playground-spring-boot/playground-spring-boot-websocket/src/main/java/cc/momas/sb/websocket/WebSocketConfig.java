package cc.momas.sb.websocket;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 * websocket 配置
 *
 * @author Sod-Momas
 * @since 2021.05.19
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /**
     * websocket 配置信息
     */
//    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean bean = new ServletServerContainerFactoryBean();
        //文本缓冲区大小
        bean.setMaxTextMessageBufferSize(8192);
        //字节缓冲区大小
        bean.setMaxBinaryMessageBufferSize(8192);
        return bean;
    }

    @Bean
    @ConditionalOnMissingBean
    public WebSocketConfigurer webSocketConfigurer() {
        return new WebSocketConfigurer() {
            @Override
            public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
                registry.addHandler(new SockJSHandler(),"/echo").withSockJS();
            }
        };
    }
}
