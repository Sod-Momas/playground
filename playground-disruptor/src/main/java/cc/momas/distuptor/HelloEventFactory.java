package cc.momas.distuptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author Sod-Momas
 * @since 2022/3/20
 */
public class HelloEventFactory implements EventFactory<MessageModel> {
    @Override
    public MessageModel newInstance() {
        return new MessageModel();
    }
}
