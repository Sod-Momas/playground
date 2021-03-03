package cc.momas.dubbo.consumer;

import cc.momas.dubbo.api.AccountService;
import cc.momas.dubbo.entity.MomasAccount;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * @author Sod-Momas
 * @since 2021-02-05
 */
@RestController
public class AccountController {
    private Logger log = Logger.getLogger(AccountController.class.getName());
    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:2200")
    private AccountService accountService;

    /**
     * 登录
     *
     * @param loginName 登录名
     * @param loginPwd  登录密码
     * @return 登录者的信息
     */
    @RequestMapping("/acc/login")
    public MomasAccount login(@RequestParam("loginName") String loginName,
                              @RequestParam("loginPwd") String loginPwd) {
        final MomasAccount acc = accountService.loginByNamePwd(loginName, loginPwd);
        log.info(String.valueOf(acc));
        return acc;
    }
}
