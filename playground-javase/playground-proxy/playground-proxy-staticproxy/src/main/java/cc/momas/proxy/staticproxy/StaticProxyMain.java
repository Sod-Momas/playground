package cc.momas.proxy.staticproxy;

public class StaticProxyMain {
    public static void main(String[] args) {
        final HomeMaster master = new HomeMaster();

        final int masterStudy = master.study();
        System.out.println("主人学习情况：" + masterStudy);
        final double masterMoney = master.makeMoney();
        System.out.println("主人赚到的钱：" + masterMoney);

        final HomeSlave slave = new HomeSlave(master);
        final int study = slave.study();
        System.out.println("奴隶学习情况：" + study);
        final double money = slave.makeMoney();
        System.out.println("奴隶赚到的钱：" + money);
    }
}
