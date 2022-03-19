package cc.momas.sd.es.sample1;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

/**
 * @author Sod-Momas
 */
@Document(indexName = "kibana_sample_data_ecommerce")
public class KibanaSampleDataEcommerce {
    @Id
    private String _id;
    @Field
    private List<String> category;
    @Field
    private String currency;
    @Field
    private String customer_first_name;
    @Field
    private String customer_full_name;
    @Field
    private String customer_gender;
    @Field
    private Integer customer_id;
    @Field
    private String customer_last_name;
    @Field
    private String customer_phone;
    @Field
    private String day_of_week;
    @Field
    private int day_of_week_i;
    @Field
    private String email;
    @Field
    private List<String> manufacturer;
    @Field(type = FieldType.Date, format = DateFormat.date_optional_time, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private Date order_date;
    @Field
    private long order_id;
    @Field
    private List<Products> products;
    @Field
    private List<String> sku;
    @Field
    private double taxful_total_price;
    @Field
    private double taxless_total_price;
    @Field
    private int total_quantity;
    @Field
    private int total_unique_products;
    @Field
    private String type;
    @Field
    private String user;
    @Field
    private Geoip geoip;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomer_first_name() {
        return customer_first_name;
    }

    public void setCustomer_first_name(String customer_first_name) {
        this.customer_first_name = customer_first_name;
    }

    public String getCustomer_full_name() {
        return customer_full_name;
    }

    public void setCustomer_full_name(String customer_full_name) {
        this.customer_full_name = customer_full_name;
    }

    public String getCustomer_gender() {
        return customer_gender;
    }

    public void setCustomer_gender(String customer_gender) {
        this.customer_gender = customer_gender;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_last_name() {
        return customer_last_name;
    }

    public void setCustomer_last_name(String customer_last_name) {
        this.customer_last_name = customer_last_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }

    public int getDay_of_week_i() {
        return day_of_week_i;
    }

    public void setDay_of_week_i(int day_of_week_i) {
        this.day_of_week_i = day_of_week_i;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(List<String> manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public List<String> getSku() {
        return sku;
    }

    public void setSku(List<String> sku) {
        this.sku = sku;
    }

    public double getTaxful_total_price() {
        return taxful_total_price;
    }

    public void setTaxful_total_price(double taxful_total_price) {
        this.taxful_total_price = taxful_total_price;
    }

    public double getTaxless_total_price() {
        return taxless_total_price;
    }

    public void setTaxless_total_price(double taxless_total_price) {
        this.taxless_total_price = taxless_total_price;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public int getTotal_unique_products() {
        return total_unique_products;
    }

    public void setTotal_unique_products(int total_unique_products) {
        this.total_unique_products = total_unique_products;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Geoip getGeoip() {
        return geoip;
    }

    public void setGeoip(Geoip geoip) {
        this.geoip = geoip;
    }

}