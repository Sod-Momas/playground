package cc.momas.sb.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

/**
 * @author Sod-Momas
 * @since 2023/7/30
 */
@Controller
public class SockJSGreetingController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public SockJSGreetingResp greeting(SockJSGreetingReq message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new SockJSGreetingResp("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
