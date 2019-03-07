package hio.dto.response;

public class DeliveryTypeDTO {

    private Integer id;
    private long mediumTime;
    private double standardTax;
    private double minDeliveryValue;
    private double freeDelivery;
    private String name;


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
}
