package hio.service;

import hio.model.menu.Category;

import java.util.List;

public interface RestaurantMenuService {

    public List<Category> list(Integer restaurantId);
    public Category save(Category category, Integer restaurant);
    public void delete(Integer categoryId);
}
