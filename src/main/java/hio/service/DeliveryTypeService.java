package hio.service;

import hio.dto.response.DeliveryTypeDTO;

import java.util.List;

public interface DeliveryTypeService {
    public List<DeliveryTypeDTO> getAllDeliveryType();
    public DeliveryTypeDTO getDeliveryTypeByUUID(String UUID);
}
