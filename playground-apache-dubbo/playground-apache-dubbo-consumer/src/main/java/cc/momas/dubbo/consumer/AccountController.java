package cc.momas.dubbo.consumer;

import cc.momas.dubbo.api.AccountService;
import cc.momas.dubbo.entity.MomasAccount;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;


/**
 * @author Sod-Momas
 * @since 2021-02-05
 */
@Service
public class AccountController {
    private final Logger log = LoggerFactory.getLogger(AccountController.class.getName());
    @DubboReference
//    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:2200")
    private AccountService accountService;

//    /**
//     * 登录
//     *
//     * @param loginName 登录名
//     * @param loginPwd  登录密码
//     * @return 登录者的信息
//     */
//    @RequestMapping("/acc/login")
//    public MomasAccount login(@RequestParam("loginName") String loginName,
//                              @RequestParam("loginPwd") String loginPwd) {
//        final MomasAccount acc = accountService.loginByNamePwd(loginName, loginPwd);
//        log.info(String.valueOf(acc));
//        return acc;
//    }

//    String hello() {
//        String result = accountService.sayHello("my account name");
//        log.info("hello result:{}", result);
//        return result;
//    }

    MomasAccount rightLogin() {
        // right
        return accountService.loginByNamePwd("sod", "P@ssw0rd");
    }

    MomasAccount errorLogin() {
        // error
        return accountService.loginByNamePwd("aaa", "bbb");
    }
}
