package hio.commons;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;


@Service
public class ResizeImage {


    private static final int IMG_WIDTH = 100;
    private static final int IMG_HEIGHT = 100;
//
//    public static void main(String [] args){
//
//        try{
//
//            BufferedImage originalImage = ImageIO.read(new File("c:\\image\\mkyong.jpg"));
//            int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
//
//            BufferedImage resizeImageJpg = resizeImage(originalImage, type, 500, 600);
//            ImageIO.write(resizeImageJpg, "jpg", new File("c:\\image\\mkyong_jpg.jpg"));
//
//            BufferedImage resizeImagePng = resizeImage(originalImage, type, 500, 150);
//            ImageIO.write(resizeImagePng, "png", new File("c:\\image\\mkyong_png.jpg"));
//
//            BufferedImage resizeImageHintJpg = resizeImageWithHint(originalImage, type);
//            ImageIO.write(resizeImageHintJpg, "jpg", new File("c:\\image\\mkyong_hint_jpg.jpg"));
//
//            BufferedImage resizeImageHintPng = resizeImageWithHint(originalImage, type);
//            ImageIO.write(resizeImageHintPng, "png", new File("c:\\image\\mkyong_hint_png.jpg"));
//
//        }catch(IOException e){
//            System.out.println(e.getMessage());
//        }
//
//    }

    public BufferedImage resizeImage(BufferedImage originalImage, int type, int width, int height){
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

        return resizedImage;
    }

    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type){

        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }

}
