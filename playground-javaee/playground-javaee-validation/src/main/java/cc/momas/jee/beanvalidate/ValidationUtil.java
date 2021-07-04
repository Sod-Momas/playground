package cc.momas.jee.beanvalidate;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationUtil {

    // 使用Hibernate 提供的验证者
    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    /**
     *  验证实体,如果验证失败会抛出异常
     * @param obj 被验证的对象
     * @param <T> 任意被 bean validation注解的类型
     */
    public static <T> void validate(T obj) {
        System.out.println(validator); // 打印验证者

        Set<ConstraintViolation<T>> result = validator.validate(obj);
        if (result.size() > 0) {
            // 有未通过验证的bean
            throw new RuntimeException(result.iterator().next().getMessage());// 抛出一条验证失败的消息
        }
    }
}
