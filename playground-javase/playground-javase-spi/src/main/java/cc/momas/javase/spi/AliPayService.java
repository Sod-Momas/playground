package cc.momas.javase.spi;

public class AliPayService implements PayService {
    @Override
    public int pay(int price) {
        System.out.print("ali payed ");
        return price;
    }
}
