package hio.model;

import javax.persistence.*;

@Entity
@Table(name="delivery_type")
public class DeliveryType {

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private long mediumTime;
    private double standardTax;
    private double minDeliveryValue;
    private double freeDelivery;
    private String deliveryTypeUUID;

    public long getMediumTime() {
        return mediumTime;
    }

    public void setMediumTime(long mediumTime) {
        this.mediumTime = mediumTime;
    }

    public double getStandardTax() {
        return standardTax;
    }

    public void setStandardTax(double standardTax) {
        this.standardTax = standardTax;
    }

    public double getMinDeliveryValue() {
        return minDeliveryValue;
    }

    public void setMinDeliveryValue(double minDeliveryValue) {
        this.minDeliveryValue = minDeliveryValue;
    }

    public double getFreeDelivery() {
        return freeDelivery;
    }

    public void setFreeDelivery(double freeDelivery) {
        this.freeDelivery = freeDelivery;
    }

    public String getDeliveryTypeUUID() {
        return deliveryTypeUUID;
    }

    public void setDeliveryTypeUUID(String deliveryTypeUUID) {
        this.deliveryTypeUUID = deliveryTypeUUID;
    }
}
