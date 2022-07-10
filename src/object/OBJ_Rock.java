package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Rock extends Projectile {
	private static int defaultSpeed;
	private static int defaultAttack;

    public OBJ_Rock(GamePanel gp){
         super(gp);
         speed = defaultSpeed;
         life = 100;
         attack=defaultAttack;
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

     public static void setDefaultSpeed(int speed) {
    	 OBJ_Rock.defaultSpeed=speed;
     }
     public static void setAttack(int attack) {
    	 OBJ_Rock.defaultAttack=attack;
     }

}