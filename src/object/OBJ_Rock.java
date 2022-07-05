package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Rock extends Projectile {
    GamePanel gp;

    public OBJ_Rock(GamePanel gp){
         super(gp);
         this.gp = gp;

         name = "Rock";
         speed = 8;
         maxLife = 80;
         life = maxLife;
         attack = 2;
         alive = false;
         getImage();

     }

     public void getImage(){
         up1 = setup("/projectile/rock_down_1", gp.TILE_SIZE, gp.TILE_SIZE);
         up2 = setup("/projectile/rock_down_1", gp.TILE_SIZE, gp.TILE_SIZE);
         down1 = setup("/projectile/rock_down_1", gp.TILE_SIZE, gp.TILE_SIZE);
         down2 = setup("/projectile/rock_down_1", gp.TILE_SIZE, gp.TILE_SIZE);
         left1 = setup("/projectile/rock_down_1", gp.TILE_SIZE, gp.TILE_SIZE);
         left2 = setup("/projectile/rock_down_1", gp.TILE_SIZE, gp.TILE_SIZE);
         right1 = setup("/projectile/rock_down_1", gp.TILE_SIZE, gp.TILE_SIZE);
         right2 = setup("/projectile/rock_down_1", gp.TILE_SIZE, gp.TILE_SIZE);
     }

}