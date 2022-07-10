package entity;

import main.GamePanel;

public class Arrow extends Projectile {


    public Arrow(GamePanel gp){
         super(gp);
         name = "Arrow";
         speed = 1;
         maxLife = 80;
         life = maxLife;
         attack = 5;
         alive = false;
         knockBackPower = 1; 
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
