package cc.momas.distuptor;

import java.io.Serializable;

/**
 * @author Sod-Momas
 * @since 2022/3/20
 */
public class MessageModel implements Serializable {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "message='" + message + '\'' +
                '}';
    }
}
