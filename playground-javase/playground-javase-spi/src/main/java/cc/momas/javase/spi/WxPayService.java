package cc.momas.javase.spi;

public class WxPayService implements PayService {
    @Override
    public int pay(int price) {
        System.out.print("wechat payed ");
        return price;
    }
}
