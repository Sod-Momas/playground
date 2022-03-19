package cc.momas.sd.es.sample1;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * Auto-generated: 2022-03-19 13:15:20
 *
 * @author Sod-Momas
 */
public class Products {

    @Field
    private double base_price;
    @Field
    private int discount_percentage;
    @Field
    private int quantity;
    @Field
    private String manufacturer;
    @Field
    private int tax_amount;
    @Field
    private int product_id;
    @Field
    private String category;
    @Field
    private String sku;
    @Field
    private double taxless_price;
    @Field
    private int unit_discount_amount;
    @Field
    private double min_price;
    @Field
    private String _id;
    @Field
    private int discount_amount;
    @Field(type = FieldType.Date, format = DateFormat.date_optional_time, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private Date created_on;
    @Field
    private String product_name;
    @Field
    private double price;
    @Field
    private double taxful_price;
    @Field
    private double base_unit_price;

    public double getBase_price() {
        return base_price;
    }

    public void setBase_price(double base_price) {
        this.base_price = base_price;
    }

    public int getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(int discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getTax_amount() {
        return tax_amount;
    }

    public void setTax_amount(int tax_amount) {
        this.tax_amount = tax_amount;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public double getTaxless_price() {
        return taxless_price;
    }

    public void setTaxless_price(double taxless_price) {
        this.taxless_price = taxless_price;
    }

    public int getUnit_discount_amount() {
        return unit_discount_amount;
    }

    public void setUnit_discount_amount(int unit_discount_amount) {
        this.unit_discount_amount = unit_discount_amount;
    }

    public double getMin_price() {
        return min_price;
    }

    public void setMin_price(double min_price) {
        this.min_price = min_price;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(int discount_amount) {
        this.discount_amount = discount_amount;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTaxful_price() {
        return taxful_price;
    }

    public void setTaxful_price(double taxful_price) {
        this.taxful_price = taxful_price;
    }

    public double getBase_unit_price() {
        return base_unit_price;
    }

    public void setBase_unit_price(double base_unit_price) {
        this.base_unit_price = base_unit_price;
    }
}