package cc.momas.spring.core;

import java.util.logging.Logger;

/**
 * @author Sod-Momas
 * @since 2022/7/23
 */
public class AnimalFactory {
    private static final AnimalFactory SIGLETON = new AnimalFactory();
    private final static Logger logger = Logger.getGlobal();

    //    public static Animal birth(String name) {
//        switch (name) {
//            case "duck":
//                return new Duck();
//            case "dog":
//                return new Dog();
//            default:
//                return null;
//        }
//    }
    public static AnimalFactory getInstance() {
        return SIGLETON;
    }

    public Dog getDog() {
        return new Dog();
    }

    public Duck getDuck() {
        return new Duck();
    }

    public interface Animal {
        void run();

        void eat();
    }

    public static class Duck implements Animal {
        @Override
        public void run() {
            logger.info("哒哒哒");
        }

        @Override
        public void eat() {
            logger.info("吃鱼");
        }

        @Override
        public String toString() {
            return "鸭鸭";
        }
    }

    public static class Dog implements Animal {
        @Override
        public void run() {
            logger.info("呼呼呼");
        }

        @Override
        public void eat() {
            logger.info("吃肉骨头");
        }

        @Override
        public String toString() {
            return "狗狗";
        }
    }
}
