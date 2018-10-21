package struts2.helloworld.action;

import com.opensymphony.xwork2.ActionSupport;
import struts2.helloworld.model.MessageStore;

public class HelloWorldAction extends ActionSupport {

    private MessageStore messageStore;

    // http://localhost:8080/basic-struts/hello.action
    public String execute() {
        messageStore = new MessageStore();
        return SUCCESS;
    }


    /**
     * <pre>
     * getter 方法是必要的, 因为struts2 是严格按照java bean的方式去存取数据,
     * 在取字段值的时候会根据字段名加上 get前缀去匹配getter方法,
     * 所以必须要有getter方法, 否则会抛调用异常
     * </pre>
     *
     * @return message
     */

    public MessageStore getMessageStore() {
        return messageStore;
    }
}
