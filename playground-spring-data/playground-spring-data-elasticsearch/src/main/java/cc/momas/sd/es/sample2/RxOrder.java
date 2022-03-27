package cc.momas.sd.es.sample2;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Sod-Momas
 * @since 2022/3/27
 */
@Document(indexName = "rx_order", shards = 1, replicas = 0)
public class RxOrder {
    @Id
    private Long id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String rxTitle;
    @Field(type = FieldType.Keyword)
    private String rxDesc;
    @Field(type = FieldType.Double)
    private Double rxPrice;
    @Field(index = false, type = FieldType.Keyword)
    private String rxRemark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRxTitle() {
        return rxTitle;
    }

    public void setRxTitle(String rxTitle) {
        this.rxTitle = rxTitle;
    }

    public String getRxDesc() {
        return rxDesc;
    }

    public void setRxDesc(String rxDesc) {
        this.rxDesc = rxDesc;
    }

    public Double getRxPrice() {
        return rxPrice;
    }

    public void setRxPrice(Double rxPrice) {
        this.rxPrice = rxPrice;
    }

    public String getRxRemark() {
        return rxRemark;
    }

    public void setRxRemark(String rxRemark) {
        this.rxRemark = rxRemark;
    }
}
