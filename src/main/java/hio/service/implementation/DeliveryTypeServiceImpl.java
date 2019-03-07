package hio.service.implementation;

import hio.commons.ObjectMapperUtils;
import hio.dto.response.DeliveryTypeDTO;
import hio.repository.DeliveryTypeRepository;
import hio.service.DeliveryTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DeliveryTypeServiceImpl implements DeliveryTypeService {

    private final DeliveryTypeRepository deliveryTypeRepository;

    @Autowired
    public DeliveryTypeServiceImpl(DeliveryTypeRepository deliveryTypeRepository){
        this.deliveryTypeRepository = deliveryTypeRepository;
    }

    @Override
    public List<DeliveryTypeDTO> getAllDeliveryType() {
        return ObjectMapperUtils.mapAll(deliveryTypeRepository.findAll(), DeliveryTypeDTO.class);
    }

    @Override
    public DeliveryTypeDTO getDeliveryTypeByUUID(String UUID) {
        return ObjectMapperUtils.map(deliveryTypeRepository.findByDeliveryTypeUUID(UUID), DeliveryTypeDTO.class);
    }
}
