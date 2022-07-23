package cc.momas.spring.core;

import java.beans.ConstructorProperties;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * @author Sod-Momas
 * @since 2022/7/23
 */
public class Cat {
    private final int age;
    private final String name;
    private final Collection<Toy> toys;
    private final static Logger logger = Logger.getGlobal();

    @ConstructorProperties({"age", "name", "toys"})
    public Cat(int age, String name, Collection<Toy> toys) {
        this.age = age;
        this.name = name;
        this.toys = toys;
    }


    public void call() {
        logger.info("喵喵喵");
    }

    public void play() {
        logger.info("玩玩具！" + name + "有这么多玩具：" + toys.toString());
    }

    @Override
    public String toString() {
        return "猫猫名字：" + name + ",猫猫年龄：" + age;
    }
}
