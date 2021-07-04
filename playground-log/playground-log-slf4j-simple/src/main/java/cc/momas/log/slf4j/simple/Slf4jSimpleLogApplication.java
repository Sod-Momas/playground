package cc.momas.log.slf4j.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Slf4jSimpleLogApplication {

    public static void main(String[] args) throws InterruptedException {
        Logger log = LoggerFactory.getLogger(Slf4jSimpleLogApplication.class);
        log.error("这是一条error消息");
        log.info("这是一条info消息");
        log.debug("this is a debug message");
        log.trace("这是一条trace消息");

        new Thread(new Runnable() {
            @Override
            public void run() {
                Logger innerLog = LoggerFactory.getLogger(getClass());
                innerLog.info("inner class info message");
            }
        }).start();

//        new Thread(()->{
//            Logger innerLog = LoggerFactory.getLogger(this.getClass());
//            innerLog.info("lambda info message");
//        }).start();

        TimeUnit.MILLISECONDS.sleep(500);
    }
}
