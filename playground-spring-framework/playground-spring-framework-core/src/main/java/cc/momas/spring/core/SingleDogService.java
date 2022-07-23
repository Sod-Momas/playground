package cc.momas.spring.core;

/**
 * @author Sod-Momas
 * @since 2022/7/23
 */
public class SingleDogService {
    private final static SingleDogService SINGLETON = new SingleDogService();

    public static SingleDogService getInstance() {
        return SINGLETON;
    }


    public int calc(int a, int b) {
        return a + b;
    }
}
