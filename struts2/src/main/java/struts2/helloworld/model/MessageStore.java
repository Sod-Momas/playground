package struts2.helloworld.model;

public class MessageStore {

    private String message;

    public MessageStore() {
        this.message = "Hello Struts User";
    }

    public String getMessage() {
        return message;
    }
}
