package cc.momas.spring.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Sod-Momas
 * @since 2022/7/16
 */
@Component
public class MyListener implements ApplicationListener<MyEvent> {
    private final Logger log = LoggerFactory.getLogger(MyListener.class);

    @Override
    public void onApplicationEvent(MyEvent event) {
        log.info("received event, processing...");
        try {
            log.info("source:{}; data:{}", event.getSource(), event.getEventData());
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.error("process error", e);
            throw new RuntimeException(e);
        }
        log.info("process done.");
    }
}
