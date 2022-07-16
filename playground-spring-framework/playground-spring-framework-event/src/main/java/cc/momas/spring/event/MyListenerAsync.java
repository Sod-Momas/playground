package cc.momas.spring.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Sod-Momas
 * @since 2022/7/16
 */
@Component
@Async
@EnableAsync
public class MyListenerAsync {
    private final Logger log = LoggerFactory.getLogger(MyListenerAsync.class);

    @EventListener(MyEventAsync.class)
    public void onApplicationEvent(MyEventAsync event) {
        log.info("async received event, processing...");
        try {
            log.info("async source:{}; data:{}", event.getSource(), event.getEventData());
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.error("async process error", e);
            throw new RuntimeException(e);
        }
        log.info(" async process done.");
    }
}
