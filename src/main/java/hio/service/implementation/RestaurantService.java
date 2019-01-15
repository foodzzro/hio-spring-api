package hio.service.implementation;

import hio.commons.AppConstants;
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


    public void createImageFolderIfNotExists() {
        if( !this.imageFolderExists() ) {
            File originalFolder = new File(AppConstants.UPLOADED_FOLDER + "/" + AppConstants.ORIGINAL_IMAGE_FOLDER);
            File thumbsFolder = new File( AppConstants.UPLOADED_FOLDER + "/" +AppConstants.THUMBS_IMAGE_FOLDER);
            originalFolder.mkdir();
            thumbsFolder.mkdir();
        }
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    private Boolean imageFolderExists() {
        File originalFolder = new File(AppConstants.UPLOADED_FOLDER + "/" + AppConstants.ORIGINAL_IMAGE_FOLDER);
        File thumbsFolder = new File( AppConstants.UPLOADED_FOLDER + "/" + AppConstants.THUMBS_IMAGE_FOLDER);
        return originalFolder.exists() && originalFolder.isDirectory() && thumbsFolder.exists() && thumbsFolder.isDirectory();
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }


    public Boolean saveImage(File image, File output, int width, int height) {

        try {
            BufferedImage resizedImage = resizeImageService.resizeImage(image, width, height);
            ImageIO.write(resizedImage, ResizeImage.getFileExtension(image), output);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
