package hio.service.implementation;

import hio.commons.ObjectMapperUtils;
import hio.dto.response.DeliveryTypeDto;
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
    public List<DeliveryTypeDto> getAllDeliveryType() {
        return ObjectMapperUtils.mapAll(deliveryTypeRepository.findAll(), DeliveryTypeDto.class);
    }

    @Override
    public DeliveryTypeDto getDeliveryTypeByUUID(String UUID) {
        return ObjectMapperUtils.map(deliveryTypeRepository.findByDeliveryTypeUUID(UUID), DeliveryTypeDto.class);
    }
}
