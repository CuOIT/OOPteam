package entity;

import main.GamePanel;

public class Rock_Bullet extends Projectile {
	private static int defaultSpeed;
	private static int defaultAttack;

    public Rock_Bullet(GamePanel gp){
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

     public static void setSpeed(int speed) {
    	 Rock_Bullet.defaultSpeed=speed;
     }
     public static void setAttack(int attack) {
    	 Rock_Bullet.defaultAttack=attack;
     }

}