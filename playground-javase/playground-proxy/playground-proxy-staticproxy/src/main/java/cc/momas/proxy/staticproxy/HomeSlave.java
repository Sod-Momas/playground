package cc.momas.proxy.staticproxy;

/**
 * 家里的奴隶
 *
 * @author Sod-Momas
 * @since 2020/11/2
 */
public class HomeSlave {
    private final HomeMaster master;

    public HomeSlave(HomeMaster master) {
        this.master = master;
    }

    /**
     * 奴隶赚钱
     *
     * @return 赚到的钱
     */
    public double makeMoney() {
        // 执行主人的方法之前可以做一些逻辑，例如打印日志
        System.out.println("主人说想赚钱了");
        return master.makeMoney();
    }

    public int study() {
        // 代理主人学习
        final int study = master.study();
        // 显然奴隶学习不怎么样
        final int realStudy = study / 2;
        // 执行完主人的方法后可以对返回值进行进一步处理
        return realStudy + 1;

    }
}
