package cc.momas.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

import java.util.logging.Logger;

/**
 * @author Sod-Momas
 * @since 2022/7/23
 */
public class SpringCoreApplication {
    private final static Logger logger = Logger.getGlobal();

    public static void main(String[] args) {
        xmlStart();
//        groovyStart();
    }

    private static void groovyStart() {
        // 使用groovy配置启动需要依赖groovy语言
        ApplicationContext context = new GenericGroovyApplicationContext("spring.groovy");
        final SpringCoreHelloService helloService = context.getBean(SpringCoreHelloService.class);
        final String result = helloService.hello("groovy momas");
        logger.info("result: " + result);
    }

    private static void xmlStart() {
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml", "spring-p.xml");
        final SpringCoreHelloService helloService = context.getBean(SpringCoreHelloService.class);
        final String result = helloService.hello("xml momas");
        logger.info("result: " + result);

        // 使用静态工厂方法实例化，而不是反射调用构造方法生成。配置在xml里
        final SingleDogService singleDogService = context.getBean("singleDogService", SingleDogService.class);
        logger.info("SingleDogService calculate resut:" + singleDogService.calc(1, 2) + "");

        // 使用实例工厂
        context.getBean("duck", AnimalFactory.Animal.class).run();
        context.getBean("dog", AnimalFactory.Animal.class).run();
        // 有参构造
        final Cat cat = context.getBean("cat", Cat.class);
        logger.info(cat.toString());
        cat.play();

        final AutowiredZoo autowiredZoo = context.getBean("autowiredZoo", AutowiredZoo.class);
        // 构造方法注入属于必填
        logger.info("动物园一定有小猫：" + autowiredZoo.getCat());
        // setter方法注入属于可选
        logger.info("动物园里可能有其他小动物" + autowiredZoo.getAnimals());

        context.getBean(Pig.class);


    }
}
