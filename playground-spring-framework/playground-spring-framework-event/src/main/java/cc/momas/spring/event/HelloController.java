package cc.momas.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Sod-Momas
 * @since 2022/7/16
 */
@RestController
public class HelloController {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/publish-event")
    public Mono<String> publishEvent() {
        final MyEvent event = new MyEvent("source", "a json");
        applicationContext.publishEvent(event);
        return Mono.just("published");
    }
    @RequestMapping("/publish-event-async")
    public Mono<String> publishEventAsync() {
        final MyEventAsync event = new MyEventAsync("source async", "a json async");
        applicationContext.publishEvent(event);
        return Mono.just("published async");
    }
}
