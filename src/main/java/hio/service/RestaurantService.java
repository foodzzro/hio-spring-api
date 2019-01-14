package hio.service;

import hio.commons.ResizeImage;
import hio.model.Restaurant;
import hio.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ResizeImage resizeImageService;

    public Restaurant saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }


    public Boolean saveImage(File image) {

        try {
            BufferedImage originalImage = ImageIO.read(image);
            int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            BufferedImage resizedImage = resizeImageService.resizeImage(originalImage, type, 500, 600);
            ImageIO.write(resizedImage, "jpg", image);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
