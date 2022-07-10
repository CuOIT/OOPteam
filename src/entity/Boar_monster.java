package entity;
import main.GamePanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import object.OBJ_Heart;
import object.OBJ_Tooth;
public class Boar_monster extends Monster{
	private static int defaultAttack;
	private static int defaultSpeed;
	private static int defaultMaxLife; 
	
	public Boar_monster(GamePanel gp) {
        super(gp);
        name="Boar_monster";
        attack=defaultAttack;
        life=maxLife=defaultMaxLife;
        speed=normalSpeed=defaultSpeed;
        solidArea=new Rectangle(3,18,42,30);
    getImage();
    }
    public void getImage(){
        up1=setup("/monster/Boar_Left_1", gp.TILE_SIZE, gp.TILE_SIZE);
		up2=setup("/monster/Boar_Left_2", gp.TILE_SIZE, gp.TILE_SIZE);
		down1=setup("/monster/Boar_Right_1", gp.TILE_SIZE, gp.TILE_SIZE);
		down2=setup("/monster/Boar_Right_2", gp.TILE_SIZE, gp.TILE_SIZE);
		left1=setup("/monster/Boar_Left_1", gp.TILE_SIZE, gp.TILE_SIZE);
		left2=setup("/monster/Boar_Left_2", gp.TILE_SIZE, gp.TILE_SIZE);
		right1=setup("/monster/Boar_Right_1", gp.TILE_SIZE, gp.TILE_SIZE);
		right2=setup("/monster/Boar_Right_2", gp.TILE_SIZE, gp.TILE_SIZE);
    }
    public void setAction(){
        actionLockCounter++;
        
		if(actionLockCounter==75) {
			Random random=new Random();
			int i = random.nextInt(100)+1;
			if(i<=25) {
				direction="up";
			}
			if(i>25 && i<50)
			{
				direction="down";
			//	System.out.println(distanceToPlayer());
			}
			if(i>50 && i<=75) {
				direction="left";
			}
			if(i>75 && i<=100) {
				direction = "right";
				//System.out.println(distanceToPlayer());
			}
			actionLockCounter=0;
		}

		if(distanceToPlayer()<5*gp.TILE_SIZE)
		{
			pathFinding();
			speed=normalSpeed+2;
		}
		else speed=normalSpeed;
    }
    //Tinh khoang cach den nhan vat

    public double distanceToPlayer(){
    	return Math.sqrt(Math.pow(this.worldX-gp.player.worldX,2)+Math.pow(this.worldY-gp.player.worldY,2));
    }
    public void pathFinding() {
    	this.speed=4;
    	if(Math.abs(this.worldY-gp.player.worldY)>10) {
    	if(this.worldY<gp.player.worldY) direction="down";
    	else direction="up";
    	}
    	else if(this.worldX<gp.player.worldX) direction="right";
    	else direction="left";
    	
    }

    public void damageReaction(){
	actionLockCounter = 0;
	direction = gp.player.direction;

}	
	public void checkDrop(){
		dropItem(new OBJ_Tooth(gp));
	}
	public static void setAttack(int attack) {
		Boar_monster.defaultAttack = attack;
	}
	public static void setDefaultSpeed(int speed) {
		Boar_monster.defaultSpeed=speed;
	}
	public static void setMaxLife(int life) {
		Boar_monster.defaultMaxLife=life;
	}
}


