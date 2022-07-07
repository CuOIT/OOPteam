package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tooth extends Entity {
    public static int numberCollected=0;
    public OBJ_Tooth(GamePanel gp){
        super(gp);
        stackable=true;
        name = "Tooth";
        down1 = setup("/objects/tooth", gp.TILE_SIZE, gp.TILE_SIZE);
    }
}