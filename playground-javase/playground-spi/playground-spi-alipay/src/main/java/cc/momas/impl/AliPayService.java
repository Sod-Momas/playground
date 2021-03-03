package cc.momas.impl;

import cc.momas.PayService;

public class AliPayService implements PayService {
    @Override
    public int pay(int price) {
        System.out.print("ali pay ");
        return price;
    }
}
