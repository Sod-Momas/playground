package cc.momas.distuptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sod-Momas
 * @since 2022/3/20
 */
public class DisruptorApplication {

    private static DisruptorMqService disruptorMqService = DisruptorMqService.INSTANCE;
    private final static Logger log = LoggerFactory.getLogger(DisruptorApplication.class);

    public static void main(String[] args) throws InterruptedException {
        log.error("开始");
        for (int i = 0; i < 100_000; i++) {
            disruptorMqService.sayHelloMq("第" + i + "条消息到了，Hello world!");
            log.info("消息队列已发送完毕");
        }
        log.error("结束");
//        //这里停止2000ms是为了确定是处理消息是异步的
//        Thread.sleep(2000);
        // 程序退出的时候需要关闭队列
        MQManager.stopDisruptor();
    }
}
