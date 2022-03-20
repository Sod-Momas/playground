package cc.momas.distuptor;

/**
 * @author Sod-Momas
 * @since 2022/3/20
 */
public interface DisruptorMqService {
    DisruptorMqService INSTANCE = new DisruptorMqServiceImpl();

    /**
     * 消息
     *
     * @param message
     */
    void sayHelloMq(String message);
}
