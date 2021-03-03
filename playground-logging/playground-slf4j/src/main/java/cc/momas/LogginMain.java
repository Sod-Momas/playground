package cc.momas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogginMain {

    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(LogginMain.class);
        log.error("这是一条error消息");
        log.info("这是一条info消息");
        log.trace("这是一条trace消息");
    }
}
