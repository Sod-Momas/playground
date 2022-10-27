package cc.momas.jee.beanvalidate;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationUtil {

    // 使用Hibernate 提供的验证者
//    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();
    private static final Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();

    /**
     * 验证实体,如果验证失败会抛出异常
     *
     * @param obj 被验证的对象
     * @param <T> 任意被 bean validation注解的类型
     */
    public static <T> void validate(T obj) {
        System.out.println(validator); // 打印验证者

        Set<ConstraintViolation<T>> validate = validator.validate(obj);
        for (ConstraintViolation<T> result : validate) {
            System.out.println(result.getRootBeanClass()+"."+result.getPropertyPath() +" -> "+ result.getMessage() + ", 实际值: " + result.getInvalidValue());
        }
    }
}
