package cc.momas.sb.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Sod-Momas
 * @since 2022/8/9
 */
public class SockJSHandler extends AbstractWebSocketHandler {
    private final Logger log = LoggerFactory.getLogger(SockJSHandler.class);
    private static final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("session open, id={}", session.getId());
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("session close, id={}", session.getId());
        sessions.remove(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("text message: {}", message.getPayload());

        final TextMessage msg = new TextMessage(session.getId() + "说：" + message.getPayload());
        broadcast(msg);
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        // 未成功
        broadcast(new TextMessage("收到文件，正在处理"));

        final Path tmp = Files.createTempFile("websocket-", ".tmp");
        final ByteBuffer payload = message.getPayload();
        payload.flip();
        final SeekableByteChannel ch = Files.newByteChannel(tmp);
        ch.write(payload);
        ch.close();
        log.info("file write to {}", tmp.toAbsolutePath());

        broadcast(new TextMessage("文件处理完成，保存至 " + tmp.toAbsolutePath()));
    }


    private void broadcast(WebSocketMessage<?> msg) throws IOException {
        for (WebSocketSession session : sessions) {
            session.sendMessage(msg);
        }
    }
}
