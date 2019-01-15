package hio.service;

import hio.dto.response.DeliveryTypeDto;

import java.util.List;

public interface DeliveryTypeService {
    public List<DeliveryTypeDto> getAllDeliveryType();
    public DeliveryTypeDto getDeliveryTypeByUUID(String UUID);
}
