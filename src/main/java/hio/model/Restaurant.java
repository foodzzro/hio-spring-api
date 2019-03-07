package hio.model;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity(name = "Restaurant")
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    private String name;
    private String description;
    private String county;
    private String city;
    private String address;
    private Double min_order;
    private String contactPhone;
    private String emailAddressAlert;
    private Boolean showInSearch;


    @Lob
    @Column
    private String image_src;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean active;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cuisine_id",referencedColumnName="id", nullable=false)
    private Cuisine cuisine;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_type_id",referencedColumnName="id", nullable=false)
    private DeliveryType deliveryType;

    @ElementCollection(fetch = FetchType.EAGER)
    List<PaymentType> paymentType;

    @Lob
    @OneToMany(
            mappedBy = "restaurant",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<DeliveryZone> deliveryZones;

    @Transactional
    public List<DeliveryZone> getDeliveryZones() {
        return deliveryZones;
    }

    public void setDeliveryZones(List<DeliveryZone> deliveryZones) {
        this.deliveryZones = deliveryZones;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Boolean getShowInSearch() {
        return showInSearch;
    }

    public void setShowInSearch(Boolean showInSearch) {
        this.showInSearch = showInSearch;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getEmailAddressAlert() {
        return emailAddressAlert;
    }

    public void setEmailAddressAlert(String emailAddressAlert) {
        this.emailAddressAlert = emailAddressAlert;
    }

    public List<PaymentType> getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(List<PaymentType> paymentType) {
        this.paymentType = paymentType;
    }
}
