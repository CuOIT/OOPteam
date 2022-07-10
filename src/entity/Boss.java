package entity;

import main.GamePanel;
import object.OBJ_Crystal;
import object.OBJ_Tooth;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Boss extends Monster {
    Projectile[] projectiles = new Rock_Bullet[8];
    private static int defaultAttack;
	private static int defaultSpeed;
	private static int defaultMaxLife; 
	public Boss(GamePanel gp) {
	    super(gp);
	    name="Boss";
		speed = normalSpeed=defaultSpeed;
	    life=maxLife=defaultMaxLife;
	    attack=defaultAttack;
	    projectiles[0]=new Rock_Bullet(gp);
		projectiles[1]=new Rock_Bullet(gp);
		projectiles[2]=new Rock_Bullet(gp);
		projectiles[3]=new Rock_Bullet(gp);
		projectiles[4]=new Rock_Bullet(gp);
		projectiles[5]=new Rock_Bullet(gp);
		projectiles[6]=new Rock_Bullet(gp);
		projectiles[7]=new Rock_Bullet(gp);
	
	    solidArea=new Rectangle(9,54,126,90);
	
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

	public static void setAttack(int attack) {
		Boss.defaultAttack = attack;
	}
	public static void setDefaultSpped(int speed) {
		Boss.defaultSpeed=speed;
	}
	public static void setMaxLife(int life) {
		Boss.defaultMaxLife=life;
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
 		
		if( attackLockCounter >= 180 && attackLockCounter<300 && actionLockCounter%40==0 ){

			direction="attack";
            projectiles[0].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "up" , true, this);
            projectiles[1].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "down" , true, this);
            projectiles[2].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "left" , true, this);
            projectiles[3].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "right" , true, this);
			projectiles[4].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "upleft" , true, this);
            projectiles[5].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "upright" , true, this);
            projectiles[6].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "downleft" , true, this);
            projectiles[7].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "downright" , true, this);
            gp.projectileList.add(projectiles[0]);
            gp.projectileList.add(projectiles[1]);
            gp.projectileList.add(projectiles[2]);
            gp.projectileList.add(projectiles[3]);
            gp.projectileList.add(projectiles[4]);
            gp.projectileList.add(projectiles[5]);
            gp.projectileList.add(projectiles[6]);
        	gp.projectileList.add(projectiles[7]);
        	
			}
			
		else if( attackLockCounter==400) {
				if(gp.monster[1][18]==null ) {
					gp.monster[1][18]=new Bat(gp);
					gp.monster[1][18].worldX=20*gp.TILE_SIZE;
					gp.monster[1][18].worldY=40*gp.TILE_SIZE;
					if(gp.monster[1][17]==null) {
					gp.monster[1][17]=new Boar_monster(gp);
					gp.monster[1][17].worldX=40*gp.TILE_SIZE;
					gp.monster[1][17].worldY=40*gp.TILE_SIZE;
					}
					if(gp.monster[1][16]==null) {
						gp.monster[1][16]=new Boar_monster(gp);
						gp.monster[1][16].worldX=29*gp.TILE_SIZE;
						gp.monster[1][16].worldY=44*gp.TILE_SIZE;
						}
					actionLockCounter=0;
					attackLockCounter=0;
					
				}
				else {
					direction="attack";
		            projectiles[0].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "up" , true, this);
		            projectiles[1].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "down" , true, this);
		            projectiles[2].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "left" , true, this);
		            projectiles[3].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "right" , true, this);
					projectiles[4].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "upleft" , true, this);
		            projectiles[5].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "upright" , true, this);
		            projectiles[6].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "downleft" , true, this);
		            projectiles[7].set(worldX+ gp.TILE_SIZE, worldY+gp.TILE_SIZE, "downright" , true, this);
		            gp.projectileList.add(projectiles[0]);
		            gp.projectileList.add(projectiles[1]);
		            gp.projectileList.add(projectiles[2]);
		            gp.projectileList.add(projectiles[3]);
		            gp.projectileList.add(projectiles[4]);
		            gp.projectileList.add(projectiles[5]);
		            gp.projectileList.add(projectiles[6]);
		        	gp.projectileList.add(projectiles[7]);
				}
			
			actionLockCounter=0;
			attackLockCounter=0;
			direction="down";
			
		}
	if(distanceToPlayer()>gp.TILE_SIZE && distanceToPlayer()<10*gp.TILE_SIZE ) 
		pathFinding();
	else speed=normalSpeed;	
	
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

		if(takingDamage == true){
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
	public void checkDrop(){
		dropItem(new OBJ_Crystal(gp));
	}
}
