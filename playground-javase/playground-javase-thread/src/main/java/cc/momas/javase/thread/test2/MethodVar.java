package cc.momas.javase.thread.test2;

/**
 * 这个测试检查了方法里的局部变量是否会被线程共享
 * 实验证明并没有被共享
 * 类的成员变量会被共享，但目前没有发现出现重复值
 */
public class MethodVar {
    public static void main(String[] args) {
        Singleton.singleton.init();
        new Thread(() -> {
            Singleton.singleton.test();
        }).start();
        new Thread(() -> {
            Singleton.singleton.test();
        }).start();
    }
}

class Singleton {

    static Singleton singleton = new Singleton();

    int classVar;

    void init() {
    }

    ;

    void test() {
        int methodVar = 0;
        for (int i = 0; i < 50; i++) {
            methodVar++;
            classVar++;
            System.out.println(
                    new StringBuilder(Thread.currentThread().getName())
                            .append(",class var is : ")
                            .append(classVar)
                            .append(",method var is : ")
                            .append(methodVar)
                            .toString()
            );
        }
    }
}