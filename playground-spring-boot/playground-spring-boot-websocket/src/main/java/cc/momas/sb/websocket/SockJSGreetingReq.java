package cc.momas.sb.websocket;

/**
 * @author Sod-Momas
 * @since 2023/7/30
 */
public class SockJSGreetingReq {

    private String name;

    public SockJSGreetingReq() {
    }

    public SockJSGreetingReq(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
