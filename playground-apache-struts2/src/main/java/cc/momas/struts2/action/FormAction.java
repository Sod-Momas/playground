package cc.momas.struts2.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 表单控制器，演示登录
 *
 * @author Sod-Momas
 * @since 2021-03-13
 */
public class FormAction {
    private static final Logger LOG = LogManager.getLogger(FormAction.class);
    private String loginName;
    private String loginPwd;
    private String message;

    public String page() {
        return "login";
    }

    public String login() {
        LOG.info("login name=" + loginName);
        LOG.info("login password=" + loginPwd);
        if (loginName == null) {
            message = null;
            return "login";
        }
        if ("sod".equals(loginName)) {
            if ("momas".equals(loginPwd)) {
                message = "登录成功";
                return "success";
            }
            message = "密码错误";
            return "error";
        }
        message = "用户名不存在";
        LOG.info("message=" + message);
        return "login";
    }

    public String getLoginName() {
        return loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public String getMessage() {
        return message;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
