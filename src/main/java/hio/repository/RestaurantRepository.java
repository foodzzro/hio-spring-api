package hio.repository;

import hio.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    Restaurant findOne(Integer id);

    List<Restaurant> findAll();
}
