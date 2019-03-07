package hio.repository;

import hio.model.DeliveryZone;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface DeliveryZoneRepository extends JpaRepository<DeliveryZone, Integer> {

    @Transactional
    List<DeliveryZone> findByRestaurantId(Integer id);
}
