package entity;
import main.GamePanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import object.OBJ_Heart;
import object.OBJ_Rock;
import object.OBJ_Tooth;

public class Bat extends Entity {
    GamePanel gp;
	public Bat(GamePanel gp) {
        super(gp);
		this.gp = gp;
    type = MONSTER_TYPE;
	name = "Bat";
    defaultSpeed = 1;
	speed = defaultSpeed;
    maxLife= 4;
    life=maxLife;
    projectile = new OBJ_Rock(gp);

    solidArea.x = 3; 
    solidArea.y= 18;
    solidArea.width = 42;
    solidArea.height = 30;
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;

    getImage();
    }
    public void getImage(){
        up1=setup("/monster/bat.up.1", gp.TILE_SIZE, gp.TILE_SIZE);
		up2=setup("/monster/bat.up.2", gp.TILE_SIZE, gp.TILE_SIZE);
		down1=setup("/monster/bat.down.1", gp.TILE_SIZE, gp.TILE_SIZE);
		down2=setup("/monster/bat.down.2", gp.TILE_SIZE, gp.TILE_SIZE);
		left1=setup("/monster/bat.left.1", gp.TILE_SIZE, gp.TILE_SIZE);
		left2=setup("/monster/bat.left.2", gp.TILE_SIZE, gp.TILE_SIZE);
		right1=setup("/monster/bat.right.1", gp.TILE_SIZE, gp.TILE_SIZE);
		right2=setup("/monster/bat.right.2", gp.TILE_SIZE, gp.TILE_SIZE);
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
        int i = new Random().nextInt(100)+1;
		if( i != 99 && type == MONSTER_TYPE && shotAvailableCounter >= 60){
			projectile.set(worldX, worldY, direction , true, this);
        	gp.projectileList.add(projectile);
			shotAvailableCounter = 0;
		}

    }

	public void checkDrop(){
		dropItem(new OBJ_Heart(gp),worldX,worldY);
	}
}

