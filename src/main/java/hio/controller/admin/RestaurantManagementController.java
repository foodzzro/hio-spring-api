package hio.controller.admin;


import hio.commons.AppConstants;
import hio.dto.response.GeneralResponseDTO;
import hio.dto.response.RestaurantResponseDTO;
import hio.model.Category;
import hio.model.Cuisine;
import hio.model.DeliveryType;
import hio.model.Restaurant;
import hio.service.implementation.RestaurantService;
import io.swagger.annotations.ApiParam;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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

    @PostMapping("/update-image/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestaurantResponseDTO uploadImage (
            @RequestParam("file") MultipartFile file,
            @ApiParam("id") @PathVariable Integer id) {

        try {
            restaurantService.createImageFolderIfNotExists();
            byte[] bytes = file.getBytes();

            Path original = Paths.get(AppConstants.UPLOADED_FOLDER + "/" + AppConstants.ORIGINAL_IMAGE_FOLDER + "/" + String.valueOf(id) + file.getOriginalFilename());
            Path thumbs = Paths.get(AppConstants.UPLOADED_FOLDER + "/" + AppConstants.THUMBS_IMAGE_FOLDER + "/" + String.valueOf(id) + file.getOriginalFilename());
            Restaurant result = restaurantService.updateImage(id, original, thumbs, bytes);
            return modelMapper.map(result, RestaurantResponseDTO.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @GetMapping(value = "/restaurant-image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getRestaurantImage( @ApiParam("id") @PathVariable Integer id ) throws ParseException, FileNotFoundException {

        Restaurant restaurant = restaurantService.getRestaurantById(id);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(restaurant.getImage_src());

        InputStream in = new DataInputStream(new FileInputStream(json.getAsString("original")));
        try {
            return IOUtils.toByteArray(in);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
