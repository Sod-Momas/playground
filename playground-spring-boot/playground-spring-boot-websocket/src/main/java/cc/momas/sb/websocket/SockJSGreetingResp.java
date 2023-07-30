package cc.momas.sb.websocket;

/**
 * @author Sod-Momas
 * @since 2023/7/30
 */
public class SockJSGreetingResp {
    private String content;

    public SockJSGreetingResp() {
    }

    public SockJSGreetingResp(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
