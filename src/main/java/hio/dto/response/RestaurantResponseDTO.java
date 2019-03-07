package hio.dto.response;

import hio.model.Cuisine;
import hio.model.PaymentType;

import java.util.List;

public class RestaurantResponseDTO {

    private Integer id;
    private String name;
    private String description;
    private String county;
    private String city;
    private String address;
    private Double min_order;
    private String contactPhone;
    private String image_src;
    private String emailAddressAlert;
    private Boolean active;

    private DeliveryTypeDTO deliveryType;
    private Cuisine cuisine;
    private List<PaymentType> paymentType;


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

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getMin_order() {
        return min_order;
    }

    public void setMin_order(Double min_order) {
        this.min_order = min_order;
    }

    public String getImage_src() {
        return image_src;
    }

    public void setImage_src(String image_src) {
        this.image_src = image_src;
    }

    public DeliveryTypeDTO getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryTypeDTO deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public String getEmailAddressAlert() {
        return emailAddressAlert;
    }

    public void setEmailAddressAlert(String emailAddressAlert) {
        this.emailAddressAlert = emailAddressAlert;
    }


    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public List<PaymentType> getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(List<PaymentType> paymentType) {
        this.paymentType = paymentType;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


}
