package cc.momas.struts2.model;

import java.util.UUID;

/**
 * 消息实体
 *
 * @author Sod-Momas
 * @since 2021.03.13
 */
public class MessageStore {

    private final String message;

    public MessageStore() {
        this.message = UUID.randomUUID().toString();
    }

    public String getMessage() {
        return message;
    }
}
