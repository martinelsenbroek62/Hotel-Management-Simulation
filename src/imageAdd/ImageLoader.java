package imageAdd;




import java.awt.Image;
import javax.imageio.ImageIO;


public class ImageLoader {
    private Image image;
    
    //this method loads image from the specified path 
    public Image loadImage(String path)
    {
        try
        {
            image=ImageIO.read(getClass().getResource(path)); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return image;
    }
}
