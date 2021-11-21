import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[10];
        getTileImage();
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        String map02 = "maps/map02.txt";
        loadMap(map02);
    }
    public void loadMap(String path){

        try{
            InputStream is = getClass().getResourceAsStream(path);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            int col = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while (col < gp.maxWorldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getTileImage(){

        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("tiles/grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("tiles/wall.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("tiles/water.png")));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("tiles/earth.png")));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("tiles/tree.png")));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("tiles/sand.png")));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void drawMap(Graphics2D g2){
        for(int i = 0; i < gp.maxWorldCol; i++){
            for (int j=0; j < gp.maxWorldRow; j++){
                int worldX = i*gp.tileSize;
                int worldY = j*gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;
                g2.drawImage(tile[mapTileNum[i][j]].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
        }

    }

    public void draw(Graphics2D g2){
        drawMap(g2);
    }
}
