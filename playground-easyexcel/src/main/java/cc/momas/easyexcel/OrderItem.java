package cc.momas.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;

import java.math.BigDecimal;

public class OrderItem {
    /**
     * 项目名
     */
    @ExcelProperty({"项目名"})
    private String itemName;
    /**
     * 项目数量
     */
    @ExcelProperty({"项目数量"})
    private BigDecimal count;
    /**
     * 单价
     */
    @ExcelProperty({"单价"})
    private BigDecimal perPrice;

    public OrderItem() {
    }

    public OrderItem(String itemName, BigDecimal count, BigDecimal perPrice) {
        this.itemName = itemName;
        this.count = count;
        this.perPrice = perPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getPerPrice() {
        return perPrice;
    }

    public void setPerPrice(BigDecimal perPrice) {
        this.perPrice = perPrice;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemName='" + itemName + '\'' +
                ", count=" + count +
                ", perPrice=" + perPrice +
                '}';
    }
}

