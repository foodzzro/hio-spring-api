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

    public long getMediumTime() {
        return mediumTime;
    }

    public void setMediumTime(long mediumTime) {
        this.mediumTime = mediumTime;
    }

    public int getStandardTax() {
        return standardTax;
    }

    public void setStandardTax(int standardTax) {
        this.standardTax = standardTax;
    }

    public int getMinDeliveryValue() {
        return minDeliveryValue;
    }

    public void setMinDeliveryValue(int minDeliveryValue) {
        this.minDeliveryValue = minDeliveryValue;
    }

    public int getFreeDelivery() {
        return freeDelivery;
    }

    public void setFreeDelivery(int freeDelivery) {
        this.freeDelivery = freeDelivery;
    }

    public String getDeliveryTypeUUID() {
        return deliveryTypeUUID;
    }

    public void setDeliveryTypeUUID(String deliveryTypeUUID) {
        this.deliveryTypeUUID = deliveryTypeUUID;
    }

    private long mediumTime;
    private int standardTax;
    private int minDeliveryValue;
    private int freeDelivery;
    private String deliveryTypeUUID;

}
