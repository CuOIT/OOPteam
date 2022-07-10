package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import object.Object;

public class Human extends Entity{

	public int numberDialogue;
	public String[][] dialogue = new String[10][20];
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	public BufferedImage attackup1, attackup2, attackdown1, attackdown2, attackleft1, attackleft2, attackright1, attackright2;
	public Rectangle attackArea = new Rectangle(0,0,0,0);
	public Entity currentWeapon;
	public Entity bow;
	public Projectile projectile;
	public ArrayList<Object> inventory = new ArrayList<>();
		
	public Human(GamePanel gp) {
		super(gp);
	}
	public void dropItem(Object droppedItem){
		for(int i = 0; i< gp.obj[0].length; i++){
			if(gp.obj[gp.currentMap][i] == null){
				gp.obj[gp.currentMap][i] = droppedItem; 
				gp.obj[gp.currentMap][i].worldX = worldX;
				gp.obj[gp.currentMap][i].worldY = worldY+gp.TILE_SIZE;
				break;
			} 
		}
	}

	public void update() {

			setAction();
			spriteCounter++;
			if(spriteCounter>12)
			{
				if(spriteNum==1)
					spriteNum=2;
			else if(spriteNum==2)
				spriteNum=1;
			spriteCounter=0;
			}

	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image=null;
		switch(direction) {
		case "up":
			if(spriteNum==1)
			image=up1;
			if(spriteNum==2)
				image=up2;
			break;
		case "down":
			if(spriteNum==1)
				image=down1;
				if(spriteNum==2)
					image=down2;
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
		 
			g2.drawImage(image,screenX,screenY,width,height,null); 
	}
	
	public void setAction() {
	}
	
}
