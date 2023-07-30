package cc.momas.sb.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author Sod-Momas
 * @since 2023/7/30
 * @see <a href="https://spring.io/guides/gs/messaging-stomp-websocket/">Configure Spring for STOMP messaging</a>
 */
@Configuration(proxyBeanMethods = false)
// 启用基于ws的消息队列
@EnableWebSocketMessageBroker
public class SockJSStompConfiguration implements WebSocketMessageBrokerConfigurer {
    /**
     * 用于创建消息队列
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket");
//        registry.addEndpoint("/portfolio").withSockJS();
    }
}
