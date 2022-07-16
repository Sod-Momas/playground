package cc.momas.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author Sod-Momas
 * @since 2022/7/16
 */
public class MyEvent extends ApplicationEvent {
    /**
     * 该类型事件携带的信息
     */
    private String eventData;

    public MyEvent(Object source, String eventData) {
        super(source);
        this.eventData = eventData;
    }

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MyEvent(Object source) {
        super(source);
    }

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }
}
