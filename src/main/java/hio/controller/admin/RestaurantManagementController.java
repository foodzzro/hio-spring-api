package hio.controller.admin;


import hio.commons.AppConstants;
import hio.dto.response.RestaurantResponseDTO;
import hio.model.Category;
import hio.model.Cuisine;
import hio.model.DeliveryType;
import hio.model.Restaurant;
import hio.service.implementation.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @PostMapping("/image")
    public String uploadImage(@RequestParam("file") MultipartFile file) {

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(AppConstants.UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            File savedImage = new File(AppConstants.UPLOADED_FOLDER + file.getOriginalFilename());
            restaurantService.saveImage(savedImage, 200 ,200);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "true";

    }

}
