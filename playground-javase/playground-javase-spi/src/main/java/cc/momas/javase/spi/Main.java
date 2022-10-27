package cc.momas.javase.spi;

import java.util.ServiceLoader;

/**
 * @author Sod-Momas
 * @since 2022/10/27
 */
public class Main {
    public static void main(String[] args) {
        final ServiceLoader<PayService> payServices = ServiceLoader.load(PayService.class);
        for (PayService payService : payServices) {
            final int price = payService.pay((int) (Math.random() * 100));
            System.out.println(price);
        }
    }
}
