package cc.momas.spring.core;

/**
 * @author Sod-Momas
 * @since 2022/7/23
 */
public class Toy {
    private String name;

    public Toy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
