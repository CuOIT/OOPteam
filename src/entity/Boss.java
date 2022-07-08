package entity;

import main.GamePanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import object.OBJ_Rock;

public class Boss extends Entity {
    GamePanel gp;
    Projectile[] projectiles = new OBJ_Rock[8];
    
	public Boss(GamePanel gp) {
    super(gp);
	this.gp = gp;
	type = MONSTER_TYPE;
	name = "Boss";
    defaultSpeed = 2;
	speed = defaultSpeed;
    maxLife= 8;
    life=maxLife;
    attack=2;
    
    projectiles[0]=new OBJ_Rock(gp);
	projectiles[1]=new OBJ_Rock(gp);
	projectiles[2]=new OBJ_Rock(gp);
	projectiles[3]=new OBJ_Rock(gp);
	projectiles[4]=new OBJ_Rock(gp);
	projectiles[5]=new OBJ_Rock(gp);
	projectiles[6]=new OBJ_Rock(gp);
	projectiles[7]=new OBJ_Rock(gp);

    solidArea.x = 9; 
    solidArea.y= 54;
    solidArea.width = 126;
    solidArea.height = 90;
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;

    getImageBoss();
    }
    public void getImageBoss(){
        up1=setup("/monster/boss.up.1", gp.TILE_SIZE*3, gp.TILE_SIZE*3);
		up2=setup("/monster/boss.up.2", gp.TILE_SIZE*3, gp.TILE_SIZE*3);
		down1=setup("/monster/boss.down.1", gp.TILE_SIZE*3, gp.TILE_SIZE*3);
		down2=setup("/monster/boss.down.2", gp.TILE_SIZE*3, gp.TILE_SIZE*3);
		left1=setup("/monster/boss.left.1", gp.TILE_SIZE*3, gp.TILE_SIZE*3);
		left2=setup("/monster/boss.left.2", gp.TILE_SIZE*3, gp.TILE_SIZE*3);
		right1=setup("/monster/boss.right.1", gp.TILE_SIZE*3, gp.TILE_SIZE*3);
		right2=setup("/monster/boss.right.2", gp.TILE_SIZE*3, gp.TILE_SIZE*3);
    }
    public void setAction(){
    	 actionLockCounter++;
 		if(actionLockCounter%75==0) {
 			Random random=new Random();
 			int i = random.nextInt(100)+1;
 			if(i<=25) {
 				direction="up";
 			}
 			if(i>25 && i<50)
 			{
 				direction="down";
 			}
 			if(i>50 && i<=75) {
 				direction="left";
 			}
 			if(i>75 && i<=100) {
 				direction = "right";
 			}
 		}

		if( shotAvailableCounter >= 180 && shotAvailableCounter<300 && actionLockCounter%30==0 ){

			direction="attack";
            projectiles[0].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "up" , true, this);
        	gp.projectileList.add(projectiles[0]);
			projectiles[1].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "upleft" , true, this);
        	gp.projectileList.add(projectiles[1]);
            projectiles[2].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "upright" , true, this);
        	gp.projectileList.add(projectiles[2]);
            projectiles[3].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "down" , true, this);
        	gp.projectileList.add(projectiles[3]);
            projectiles[4].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "downleft" , true, this);
        	gp.projectileList.add(projectiles[4]);
            projectiles[5].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "downright" , true, this);
        	gp.projectileList.add(projectiles[5]);
            projectiles[6].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "left" , true, this);
        	gp.projectileList.add(projectiles[6]);
            projectiles[7].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "right" , true, this);
        	gp.projectileList.add(projectiles[7]);
        	
			}
			
		else if( shotAvailableCounter==400) {
			System.out.println();
			for(int i=0;i<gp.monster[0].length-1;i++) {
				if(gp.monster[1][i]==null && gp.monster[1][i+1]==null) {
					gp.monster[1][i]=new Boar_monster(gp);
					gp.monster[1][i].worldX=20*gp.TILE_SIZE;
					gp.monster[1][i].worldY=40*gp.TILE_SIZE;
					gp.monster[1][i+1]=new Boar_monster(gp);
					gp.monster[1][i+1].worldX=40*gp.TILE_SIZE;
					gp.monster[1][i+1].worldY=40*gp.TILE_SIZE;
					actionLockCounter=0;
					shotAvailableCounter=0;
					break;
				}
			}
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
			
		case "attack":
			if(spriteNum==1)
			image=right1;
			if(spriteNum==2)
				image=right2;
		break;
		}

		if(invincible == true){
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
		int rightOffset=gp.SCREEN_WIDTH-gp.player.screenX;
		 if(rightOffset>gp.worldWidth-gp.player.worldX) {
			 screenX=gp.SCREEN_WIDTH-(gp.worldWidth-worldX);
		 }
		int bottomOffset=gp.SCREEN_HEIGHT-gp.player.screenY;
			if(bottomOffset>gp.worldHeight-gp.player.worldY) {
				screenY=gp.SCREEN_HEIGHT- (gp.worldHeight-worldY);
		 }		
		 // THEM TU DONG  THANH MAU CUA CON QUAI(DANG)
		
			double oneScale = (double)gp.TILE_SIZE/maxLife;
			double hpBarValue = oneScale*life;

			g2.setColor(new Color(35, 35, 35));
			g2.fillRect(screenX -1, screenY -16, gp.TILE_SIZE*3, 12);
			
			g2.setColor(new Color(255,0,30));
			g2.fillRect(screenX , screenY - 15, (int)hpBarValue*3, 10);
			
			
		
		g2.drawImage(image,screenX,screenY,width,height,null); 
	
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		changeAlpha(g2,1F);
	
	}
}
