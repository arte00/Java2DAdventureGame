import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjectKey extends SuperObject{

    public ObjectKey(){
        this.name = "key";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("objects/key.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
