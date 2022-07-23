package cc.momas.spring.core;

import java.util.logging.Logger;

/**
 * @author Sod-Momas
 * @since 2022/7/23
 */
public class Pig {
    private final static Logger log = Logger.getGlobal();
    private float weight;
    private long height;

    public static Pig getInstance() {
        log.info("生成新猪猪");
        return new Pig();
    }

    public void init() {
        log.info("猪猪初始化！");
    }

    public void close() {
        log.info("猪猪自闭");
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "猪猪";
    }
}
