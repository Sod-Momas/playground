package cc.momas.spring.core;

/**
 * @author Sod-Momas
 * @since 2022/7/23
 */
public class AnimalFactory {
    private static final AnimalFactory SIGLETON = new AnimalFactory();

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
            System.out.println("哒哒哒");
        }

        @Override
        public void eat() {
            System.out.println("吃鱼");
        }
    }

    public static class Dog implements Animal {
        @Override
        public void run() {
            System.out.println("呼呼呼");
        }

        @Override
        public void eat() {
            System.out.println("吃肉骨头");
        }
    }
}
