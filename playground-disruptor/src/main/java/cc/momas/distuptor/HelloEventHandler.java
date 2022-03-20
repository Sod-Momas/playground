package cc.momas.distuptor;

import com.lmax.disruptor.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sod-Momas
 * @since 2022/3/20
 */
public class HelloEventHandler implements EventHandler<MessageModel> {
    private final Logger log = LoggerFactory.getLogger(HelloEventHandler.class);

    @Override
    public void onEvent(MessageModel event, long sequence, boolean endOfBatch) {
        try {
            //这里停止1000ms是为了确定消费消息是异步的
//            Thread.sleep(200);
            log.info("消费者处理消息开始");
            if (event != null) {
                log.info("消费者消费的信息是：{}",event);
            }
        } catch (Exception e) {
            log.info("消费者处理消息失败");
        }
        log.info("消费者处理消息结束");
    }
}