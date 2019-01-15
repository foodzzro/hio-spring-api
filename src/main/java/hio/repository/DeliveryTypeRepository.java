package hio.repository;

import hio.model.DeliveryType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryTypeRepository extends JpaRepository<DeliveryType, Long> {
    DeliveryType findByDeliveryTypeUUID(String UUID);
}
