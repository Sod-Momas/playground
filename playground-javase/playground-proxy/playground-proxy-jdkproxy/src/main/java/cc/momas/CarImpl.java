package cc.momas;

/**
 * @author Sod-Momas
 * @since 2020/11/2
 */
public class CarImpl implements Car {
    public void sellCar() {
        System.out.println("卖出一辆车");
    }

    public void fix() {
        System.out.println("修理一辆车");
    }

    public void show(String conent) {
        System.out.println(conent);
    }

    public int returnValue() {
        return 100;
    }
}
