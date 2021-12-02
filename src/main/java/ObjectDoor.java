import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjectDoor extends SuperObject{

    public ObjectDoor(){
        this.name = "Door";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("objects/door.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
