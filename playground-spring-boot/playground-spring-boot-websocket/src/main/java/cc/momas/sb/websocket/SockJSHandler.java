package cc.momas.sb.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

/**
 * @author Sod-Momas
 * @since 2022/8/9
 */
public class SockJSHandler extends AbstractWebSocketHandler {
    private final Logger log = LoggerFactory.getLogger(SockJSHandler.class);

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("text message: {}", message.getPayload());

        final TextMessage send = new TextMessage(session.getId() + "说：" + message.getPayload());
        session.sendMessage(send);
    }
}
