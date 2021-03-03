package cc.momas.dubbo.provider;

import cc.momas.dubbo.api.AccountService;
import cc.momas.dubbo.constant.BloodTypeEnum;
import cc.momas.dubbo.entity.MomasAccount;
import cc.momas.dubbo.exception.AccountNotFoundException;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 * @author Sod-Momas
 * @since 2021-02-05
 */
@DubboService(version = "1.0.0")
public class AccountServiceImpl implements AccountService {
    private final static Logger log = Logger.getLogger(AccountServiceImpl.class.getName());
    /**
     * The default value of ${dubbo.application.name} is ${spring.application.name}
     */
    @Value("${dubbo.application.name}")
    private String serviceName;

    @Override
    public MomasAccount loginByNamePwd(String loginName, String loginPwd) throws AccountNotFoundException {
        log.info("response from " + serviceName);
        if ("sod".equals(loginName) && "P@ssw0rd".equals(loginPwd)) {
            final MomasAccount acc = new MomasAccount();
            acc.setLoginName(loginName);
            acc.setLoginPwd(null);
            acc.setBirthday(LocalDateTime.now());
            acc.setWeight(BigDecimal.valueOf(123454321.67890));
            acc.setBloodType(BloodTypeEnum.B);
            return acc;
        }
        throw new AccountNotFoundException();
    }
}
