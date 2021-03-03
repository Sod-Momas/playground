package cc.momas;

import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Date;
import java.util.List;

public class Order {
    /**
     * 患者名
     */
    @ExcelProperty({"患者名"})
    private String patientName;
    /**
     * 患者年龄
     */
    @ExcelProperty({"患者年龄"})
    private Integer patientAge;
    /**
     * 订单创建日期
     */
    @ExcelProperty({"订单创建日期"})
    private Date createTime;

//    @ExcelProperty(value = {"订单详情"})
    private List<OrderItem> orderItemList;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "patientName='" + patientName + '\'' +
                ", patientAge=" + patientAge +
                ", createTime=" + createTime +
                ", orderItemList=" + orderItemList +
                '}';
    }
}
