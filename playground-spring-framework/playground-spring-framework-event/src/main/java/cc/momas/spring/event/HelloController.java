package cc.momas.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
    private ApplicationEventPublisher applicationContext;

    @RequestMapping("/publish-event")
    public Mono<String> publishEvent() {
        final MyEvent event = new MyEvent(this, "a json");
        applicationContext.publishEvent(event);
        return Mono.just("published");
    }

    @RequestMapping("/publish-event-async")
    public Mono<String> publishEventAsync() {
        final MyEventAsync event = new MyEventAsync(this, "a json async");
        applicationContext.publishEvent(event);
        return Mono.just("published async");
    }
}
