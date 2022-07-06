package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Arrow extends Projectile {
    GamePanel gp;

    public OBJ_Arrow(GamePanel gp){
         super(gp);
         this.gp = gp;

         type = arrowType;//them dong code nay
         name = "Fireball";
         speed = 8;
         maxLife = 80;
         life = maxLife;
         attack = 2;
         alive = false;
         knockBackPower = 5; 
     	stackable=true;//them dong code nay
         getImage();

     }

     public void getImage(){
         up1 = setup("/projectile/fireball_up_1", gp.TILE_SIZE, gp.TILE_SIZE);
         up2 = setup("/projectile/fireball_up_2", gp.TILE_SIZE, gp.TILE_SIZE);
         down1 = setup("/projectile/fireball_down_1", gp.TILE_SIZE, gp.TILE_SIZE);
         down2 = setup("/projectile/fireball_down_2", gp.TILE_SIZE, gp.TILE_SIZE);
         left1 = setup("/projectile/fireball_left_1", gp.TILE_SIZE, gp.TILE_SIZE);
         left2 = setup("/projectile/fireball_left_2", gp.TILE_SIZE, gp.TILE_SIZE);
         right1 = setup("/projectile/fireball_right_1", gp.TILE_SIZE, gp.TILE_SIZE);
         right2 = setup("/projectile/fireball_right_2", gp.TILE_SIZE, gp.TILE_SIZE);
     }

}