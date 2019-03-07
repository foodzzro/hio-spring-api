package hio.service.implementation;

import hio.model.Restaurant;
import hio.model.menu.Category;
import hio.repository.RestaurantMenuRepository;
import hio.service.RestaurantMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantMenuServiceImpl implements RestaurantMenuService {

    @Autowired
    RestaurantMenuRepository restaurantMenuRepository;

    @Autowired
    RestaurantService restaurantService;

    @Override
    public List<Category> list(Integer restaurantId) {
        return restaurantMenuRepository.findByRestaurantId(restaurantId);
    }

    public Category save(Category category, Integer restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        category.setRestaurant(restaurant);
        restaurantMenuRepository.save(category);
        return category;
    }

    @Override
    public void delete(Integer id) {
        Category category = restaurantMenuRepository.findOne(id);
        restaurantMenuRepository.delete(category);
    }
}
