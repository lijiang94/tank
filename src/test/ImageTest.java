package test;

import com.jali.tank.ResourceManager;
import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author lijiang
 * @create 2020-04-22 21:35
 */
public class ImageTest {

    @Test
    public void test(){
        try {
            // 放在内存里的image
            BufferedImage read = ImageIO.read(new File(("C:\\Users\\jali\\Pictures\\1.jpg")));
            Assert.assertNotNull(read);

            BufferedImage image = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            Assert.assertNotNull(image);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
