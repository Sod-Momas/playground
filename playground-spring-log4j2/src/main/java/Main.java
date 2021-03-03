import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(Main.class);
        System.out.println(log.getClass().getPackageName());
        
        log.debug("debug");
        log.info("info");

    }
}
