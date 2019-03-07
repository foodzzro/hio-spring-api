package hio.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import hio.dto.response.DeliveryTypeDTO;
import hio.model.Cuisine;
import hio.model.PaymentType;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantDetailsRequestDTO {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmailAlerts() {
        return emailAlerts;
    }

    public void setEmailAlerts(String emailAlerts) {
        this.emailAlerts = emailAlerts;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getMin_order() {
        return min_order;
    }

    public void setMin_order(Double min_order) {
        this.min_order = min_order;
    }

    public DeliveryTypeDTO getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryTypeDTO deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getImage_src() {
        return image_src;
    }

    public void setImage_src(String image_src) {
        this.image_src = image_src;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public List<PaymentType> getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(List<PaymentType> paymentType) {
        this.paymentType = paymentType;
    }

    @JsonProperty("ID")
    private Integer id;

    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("min_order")
    private Double min_order;

    @JsonProperty("emailAlerts")
    private String emailAlerts;

    @JsonProperty("deliveryType")
    private DeliveryTypeDTO deliveryType;

    @JsonProperty("cuisine")
    private Cuisine cuisine;

    @JsonProperty("image_src")
    private String image_src;

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;

    @JsonProperty("county")
    private String county;

    @JsonProperty("paymentType")
    private List<PaymentType> paymentType;

}
