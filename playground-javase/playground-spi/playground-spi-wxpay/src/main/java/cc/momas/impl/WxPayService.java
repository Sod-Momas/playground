package cc.momas.impl;

import cc.momas.PayService;

public class WxPayService implements PayService {
    @Override
    public int pay(int price) {
        System.out.print("wechat payed ");
        return price;
    }
}
