package cc.momas;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        final ServiceLoader<PayService> payServices
                = ServiceLoader.load(PayService.class);
        for (PayService payService : payServices) {
            final int price = payService.pay((int) (Math.random() * 100));
            System.out.println(price);
        }
    }
}
