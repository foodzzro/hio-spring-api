package hio.repository;

import hio.model.menu.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantMenuRepository extends JpaRepository<Category, Integer> {

    public List<Category> findByRestaurantId(Integer restaurantId);

}
