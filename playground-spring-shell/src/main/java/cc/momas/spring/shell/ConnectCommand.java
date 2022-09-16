package cc.momas.spring.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.concurrent.TimeUnit;

/**
 * @author Sod-Momas
 * @since 2022/9/16
 */
@ShellComponent
public class ConnectCommand {

    private boolean connected;

    @ShellMethod("Connect to the server.")
    public void connect(String user, String password) {
        connected = true;
        System.out.println("connected!");
    }

    @ShellMethod("Disconnect from the server.")
    public String disconnect() {
        connected = false;
        return "disconnected!";
    }

    @ShellMethod("Download the nuclear codes.")
    // @ShellMethodAvailability("availabilityCheck") 表示调用该方法之前，使用 availabilityCheck 检查是否可用
    @ShellMethodAvailability("availabilityCheck")
    public String download() throws InterruptedException {
        System.out.println("downloading...");
        TimeUnit.SECONDS.sleep(1);
        return "downloaded.";
    }

    public Availability availabilityCheck() {
        return connected
                ? Availability.available()
                : Availability.unavailable("you are not connected");
    }

    // 表示 download disconnect 两个方法需要经过该方法验证
//    @ShellMethodAvailability({"download", "disconnect"})
//    public Availability downloadAvailability() {
//        return connected
//                ? Availability.available()
//                : Availability.unavailable("you are not connected");
//    }
}
