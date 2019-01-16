package hio.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String county;
    private String city;
    private String address;
    private Double min_order;
    private String contact_phone;

    @Lob
    @Column
    private String image_src;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean active;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="category_id",referencedColumnName="id", nullable=false)
    private Category category;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="cuisine_id",referencedColumnName="id", nullable=false)
    private Cuisine cuisine;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="delivery_type_id",referencedColumnName="id", nullable=false)
    private DeliveryType deliveryType;

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


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
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
}
