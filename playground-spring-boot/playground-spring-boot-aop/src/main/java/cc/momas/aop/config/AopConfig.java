package cc.momas.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Aspect
@Configuration
public class AopConfig {

    private static final Logger log = LoggerFactory.getLogger(AopConfig.class);

    /**
     * <pre>
     *  execution([可见性] 返回类型 [声明类型].方法名(参数) [异常])
     * </pre>
     * <li> '*' 匹配任意字符</li>
     * <li>'+' 匹配一个或多个字符。一般用于表示某个类的所有子类</li>
     * <li> '..' 一般用于匹配多个包，多个参数</li>
     */
    @Pointcut("execution(public * cc.momas.aop..*(..))")
    public void pointCut() {
//        //@Around
//        try {
//            try {
//                //@Before
//                method.invoke(..);
//            } finally {
//                //@After
//            }
//            //@AfterReturning
//        } catch (Exception e) {
//            //@AfterThrowing
//        }
    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        log.info("doAround");
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("doBefore");
    }

    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("doAfter");
    }

    @AfterReturning(value = "pointCut()", returning = "returnValue")
    public void doAfterReturning(JoinPoint joinPoint, Object returnValue) {
        log.info("doAfterReturning");
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "t")
    public void doAfterThrowing(Throwable t) {
        log.info("doAfterThrowing");
    }
}
