package cc.momas.dubbo.api;

import cc.momas.dubbo.entity.MomasAccount;
import cc.momas.dubbo.exception.AccountNotFoundException;

import java.util.concurrent.CompletableFuture;

/**
 * @author Sod-Momas
 * @since 2021-02-03
 */
public interface AccountService {
    /**
     * 使用登录名和密码登录
     *
     * @param loginName 登录名
     * @param loginPwd  密码
     * @return 登录成功返回账号信息
     * @throws AccountNotFoundException 账户找不到时抛此异常
     */
    MomasAccount loginByNamePwd(String loginName, String loginPwd) throws AccountNotFoundException;

    String sayHello(String name);

//    default CompletableFuture<String> sayHelloAsync(String name) {
//        return CompletableFuture.completedFuture(sayHello(name));
//    }
}
