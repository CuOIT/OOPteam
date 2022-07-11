package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tooth extends Object {
    public static int numberCollected=0;
    public OBJ_Tooth(GamePanel gp){
        super(gp);
        stackable=true;
        collision=false;
        name = "Tooth";
        down1 = setup("/objects/tooth", gp.TILE_SIZE, gp.TILE_SIZE);
		description = "[" + name + "]Give them to the headman";

    }
}