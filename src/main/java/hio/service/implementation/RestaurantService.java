package hio.service.implementation;

import hio.commons.AppConstants;
import hio.commons.ResizeImage;
import hio.helpers.CommonServiceHelper;
import hio.model.DeliveryZone;
import hio.model.Restaurant;
import hio.repository.DeliveryZoneRepository;
import hio.repository.RestaurantRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    DeliveryZoneRepository deliveryZoneRepository;

    @Autowired
    ResizeImage resizeImageService;

    public void createImageFolderIfNotExists() {
        if( !this.imageFolderExists() ) {
            File originalFolder = new File(AppConstants.UPLOADED_FOLDER + "/" + AppConstants.ORIGINAL_IMAGE_FOLDER);
            File thumbsFolder = new File( AppConstants.UPLOADED_FOLDER + "/" +AppConstants.THUMBS_IMAGE_FOLDER);
            originalFolder.mkdir();
            thumbsFolder.mkdir();
        }
    }

    public Restaurant getRestaurantById( Integer id ) {
        return restaurantRepository.findOne(id);
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        restaurant.setUuid(CommonServiceHelper.generateUUID());
        restaurantRepository.save(restaurant);
        return restaurant;
    }


    public Restaurant updateRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }


    public Restaurant updateImage(Integer id, Path original, Path thumbs, byte[] bytes) throws IOException {
        Files.write(original, bytes);
        this.saveImageFile(original.toFile(), thumbs.toFile(), 500, 600);
        return this.saveRestaurantImagePath(id, original.toString(), thumbs.toString());

    }

    private Boolean imageFolderExists() {
        File originalFolder = new File(AppConstants.UPLOADED_FOLDER + "/" + AppConstants.ORIGINAL_IMAGE_FOLDER);
        File thumbsFolder = new File( AppConstants.UPLOADED_FOLDER + "/" + AppConstants.THUMBS_IMAGE_FOLDER);
        return originalFolder.exists() && originalFolder.isDirectory() && thumbsFolder.exists() && thumbsFolder.isDirectory();
    }


    private void saveImageFile(File image, File output, int width, int height) {

        try {
            BufferedImage resizedImage = resizeImageService.resizeImage(image, width, height);
            ImageIO.write(resizedImage, ResizeImage.getFileExtension(image), output);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private Restaurant saveRestaurantImagePath(Integer restaurantId, String original, String thumb) {

        JSONObject json = new JSONObject();
        json.put("original", original);
        json.put("thumb", thumb);

        Restaurant restaurant = restaurantRepository.findOne(restaurantId);
        restaurant.setImage_src(json.toJSONString());
        return restaurantRepository.save(restaurant);
    }

    public DeliveryZone saveRestaurantDeliveryZones(Integer restaurantId, DeliveryZone deliveryZone) {

        Restaurant restaurant = this.getRestaurantById(restaurantId);
        deliveryZone.setRestaurant(restaurant);
        deliveryZoneRepository.save(deliveryZone);
        return deliveryZone;
    }

    public void updateRestaurantDeliveryZone(Integer restaurantId, DeliveryZone deliveryZone) {

        Restaurant restaurant = this.getRestaurantById(restaurantId);
        deliveryZone.setRestaurant(restaurant);
        deliveryZoneRepository.save(deliveryZone);

    }

    public void deleteDeliveryZone(Integer id) {
        deliveryZoneRepository.delete(id);
    }

    public List<DeliveryZone> getDeliveryZones(Integer id) {
        return deliveryZoneRepository.findByRestaurantId(id);
    }
}
