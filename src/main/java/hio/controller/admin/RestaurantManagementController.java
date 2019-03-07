package hio.controller.admin;
import com.fasterxml.jackson.databind.ObjectMapper;
import hio.commons.AppConstants;
import hio.commons.ObjectMapperUtils;
import hio.dto.DeliveryZonesDTO;
import hio.dto.request.RestaurantDetailsRequestDTO;
import hio.dto.response.RestaurantResponseDTO;
import hio.model.DeliveryZone;
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
import java.util.Map;

@RestController
@RequestMapping("/${api}/${admin.prefix-url}/${admin.restaurants}")
public class RestaurantManagementController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurant/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestaurantResponseDTO item(
            @ApiParam("id") @PathVariable Integer id
    ) {
        Restaurant rest = restaurantService.getRestaurantById(id);
        return modelMapper.map(rest, RestaurantResponseDTO.class);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RestaurantResponseDTO update(
            @RequestBody Map<String, Object> payload
    ) throws IOException {


        RestaurantDetailsRequestDTO restaurantDetailsRequestDTO = modelMapper.map(payload, RestaurantDetailsRequestDTO.class);
        Restaurant rest = modelMapper.map(restaurantDetailsRequestDTO, Restaurant.class);
        restaurantService.saveRestaurant(rest);

        return modelMapper.map(rest, RestaurantResponseDTO.class);
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

    @GetMapping(value = "/restaurant-image/{id}", produces={MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
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


    @PostMapping("/delivery-zones/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DeliveryZonesDTO saveDeliveryZones(
            @ApiParam("id") @PathVariable Integer id,
            @RequestBody Map<String, Object> payload) {

        DeliveryZonesDTO deliveryZonesDTO = modelMapper.map(payload, DeliveryZonesDTO.class);
        DeliveryZone deliveryZone = modelMapper.map(deliveryZonesDTO, DeliveryZone.class);
        restaurantService.saveRestaurantDeliveryZones(id, deliveryZone);
        deliveryZonesDTO.setId(deliveryZone.getId());
        return deliveryZonesDTO;
    }

    @PutMapping("/delivery-zones/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateDeliveryZones(
            @ApiParam("id") @PathVariable Integer id,
            @RequestBody Map<String, Object> payload) {

        DeliveryZonesDTO deliveryZonesDTO = modelMapper.map(payload, DeliveryZonesDTO.class);
        DeliveryZone deliveryZone = modelMapper.map(deliveryZonesDTO, DeliveryZone.class);
        restaurantService.updateRestaurantDeliveryZone(id, deliveryZone);
    }

    @GetMapping("/delivery-zones/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<DeliveryZonesDTO> getDeliveryZones(@ApiParam("id") @PathVariable Integer id) {

        List<DeliveryZone> deliveryZoneList = restaurantService.getDeliveryZones(id);
        return ObjectMapperUtils.mapAll(deliveryZoneList, DeliveryZonesDTO.class);
    }

    @DeleteMapping("/delivery-zones/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteDeliveryZone(@ApiParam("id") @PathVariable Integer id) {

        restaurantService.deleteDeliveryZone(id);
    }

}
