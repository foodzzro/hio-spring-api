package hio.dto.response;

public class DeliveryTypeDto {
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

    private int standardTax;
    private int minDeliveryValue;
    private int freeDelivery;
    private String deliveryTypeUUID;
}
