package hio.controller.admin;


import hio.dto.response.RestaurantResponseDTO;
import hio.model.Category;
import hio.model.Cuisine;
import hio.model.DeliveryType;
import hio.model.Restaurant;
import hio.service.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/${api}/${admin.prefix-url}/${admin.restaurants}")
public class RestaurantManagementController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Boolean create() {
        Restaurant rest = new Restaurant();

        Category category = new Category();
        category.setName("Salate");

        Cuisine cuisine = new Cuisine();
        cuisine.setName("italieneasca");

        DeliveryType deliveryType = new DeliveryType();
        deliveryType.setName("pe jos");

        rest.setCategory(category);
        rest.setDeliveryType(deliveryType);
        rest.setCuisine(cuisine);
        rest.setName("new restaurant");
        rest.setActive(true);

        restaurantService.saveRestaurant(rest);
        return true;
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<RestaurantResponseDTO> list() {

        List<Restaurant> restaurantList = restaurantService.getAllRestaurants();
        List<RestaurantResponseDTO> restDtoList = new ArrayList<RestaurantResponseDTO>();

        for(Restaurant rest : restaurantList) {
            restDtoList.add(modelMapper.map(rest, RestaurantResponseDTO.class));
        }
        return restDtoList;
    }

}
