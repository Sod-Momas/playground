package cc.momas.easyexcel;

import java.math.BigDecimal;
import java.util.*;

public class OrderFactory {

    public static Order getOrder() {
        final List<OrderItem> orderItems = Arrays.asList(
                new OrderItem(UUID.randomUUID().toString(), BigDecimal.valueOf(Math.random()), BigDecimal.valueOf(Math.random())),
                new OrderItem(UUID.randomUUID().toString(), BigDecimal.valueOf(Math.random()), BigDecimal.valueOf(Math.random())),
                new OrderItem(UUID.randomUUID().toString(), BigDecimal.valueOf(Math.random()), BigDecimal.valueOf(Math.random()))
        );

        final Order order = new Order();
        order.setCreateTime(new Date());
        order.setPatientAge(new Random(100).nextInt());
        order.setPatientName("name" + System.currentTimeMillis());
//        order.setOrderItemList(orderItems);
        return order;
    }
}
