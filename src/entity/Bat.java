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
    
    public void draw(Graphics2D g2) {
		BufferedImage image=null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldX + gp.player.screenY;
			
		switch(direction) {
		case "up":
			if(this.worldX<gp.player.worldX) {
				if(spriteNum==1) image=right1;
				if(spriteNum==2) image=right2;
			}
	    	else 
	    	{
	    		if(spriteNum==1) image=left1;
				if(spriteNum==2) image=left2;
	    	}
			break;
		case "down":
			if(this.worldX<gp.player.worldX) {
				if(spriteNum==1) image=right1;
				if(spriteNum==2) image=right2;
			}
	    	else 
	    	{
	    		if(spriteNum==1) image=left1;
				if(spriteNum==2) image=left2;
	    	}
			break;
		case "left":
			if(spriteNum==1)
				image=left1;
				if(spriteNum==2)
					image=left2;
			break;
		case "right":
			if(spriteNum==1)
				image=right1;
				if(spriteNum==2)
					image=right2;
			break;
		}

		if(invincible == true){
			hpBarOn = true;
			hpBarCounter = 0;
			changeAlpha(g2, 0.4F);
		}
		if(dying == true){
			dyingAnimation(g2);
		}
	
		screenX=worldX-gp.player.worldX+gp.player.screenX;
		screenY=worldY-gp.player.worldY+gp.player.screenY;
		if(gp.player.screenX>gp.player.worldX) {
			screenX=worldX;
		}
		if(gp.player.screenY>gp.player.worldY) {
			screenY=worldY;
		}
		int rightOffset=gp.player.screenX;
		 if(rightOffset>gp.worldWidth-gp.player.worldX) {
			 screenX=gp.SCREEN_WIDTH-(gp.worldWidth-worldX);
		 }
		int bottomOffset=gp.SCREEN_HEIGHT-gp.player.screenY;
			if(bottomOffset>gp.worldHeight-gp.player.worldY) {
				screenY=gp.SCREEN_HEIGHT- (gp.worldHeight-worldY);
		 }		
		 // THEM TU DONG  THANH MAU CUA CON QUAI(DANG)
		 if(hpBarOn == true ){
			double oneScale = (double)gp.TILE_SIZE/maxLife;
			double hpBarValue = oneScale*life;

			g2.setColor(new Color(35, 35, 35));
			g2.fillRect(screenX -1, screenY - 16, gp.TILE_SIZE +2, 12);
			
			g2.setColor(new Color(255,0,30));
			g2.fillRect(screenX , screenY - 15, (int)hpBarValue, 10);
			
			hpBarCounter++;

			if(hpBarCounter > 1000){
				hpBarCounter = 0;
				hpBarOn = false;
			}
		}	
		
		g2.drawImage(image,screenX,screenY,width,height,null); 
	
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		changeAlpha(g2,1F);

	}


    public void damageReaction(){
	actionLockCounter = 0;
	direction = gp.player.direction;

}	
	public void checkDrop(){
		dropItem(new OBJ_Heart(gp),worldX,worldY);
	}
}

