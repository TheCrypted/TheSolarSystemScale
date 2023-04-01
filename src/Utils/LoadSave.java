package Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static final String PLAYER_ATLAS = "EarthRotating.png";
    public static final String MARS_ATLAS = "MarsRotating.png";
    public static final String MERCURY_ATLAS = "MercuryRotating.png";
    public static final String VENUS_ATLAS = "VenusRotating.png";
    public static BufferedImage getPlayerAtlas(String filename){
        BufferedImage image = null;
        InputStream is = LoadSave.class.getResourceAsStream(filename);
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                is.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return image;
    }
}
