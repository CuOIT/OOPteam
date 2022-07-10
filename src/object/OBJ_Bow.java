package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bow extends Object {
    public OBJ_Bow(GamePanel gp){
         super(gp);
         name = "Bow";
         stackable=true;
         collision=false;
         down1 = setup("/objects/bow", gp.TILE_SIZE, gp.TILE_SIZE);
     }



}
