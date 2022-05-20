package entity;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.*;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
public class Player extends Entity{

	KeyHandler keyH;
	public final int screenX,screenY;
	public Player(GamePanel gp,KeyHandler keyH)
	{
		super(gp); 
		this.keyH=keyH;
		collision=true;
		screenX=gp.screenWidth/2-(gp.tileSize/2);
		screenY=gp.screenHeight/2-(gp.tileSize/2);
		//set character in the center
		
		solidArea=new Rectangle();
		solidArea.x=8;
		solidArea.y=16;
		solidAreaDefaultX=solidArea.x;
		solidAreaDefaultY=solidArea.y;
		solidArea.width=32;
		solidArea.height=32;
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		worldX=gp.tileSize*15;
		worldY=gp.tileSize*16;
		//set character in the center
		speed=10;
		direction="down";
		maxLife=10;
		life=10;
	}
	
	public void getPlayerImage() {
		up1=setup("/player/Up1");
		up2=setup("/player/Up2");
		down1=setup("/player/Down1");
		down2=setup("/player/Down2");
		left1=setup("/player/Left1");
		left2=setup("/player/Left2");
		right1=setup("/player/Right1");
		right2=setup("/player/Right2");
	}
	
	public void update() {
		if(keyH.upPressed==true || keyH.downPressed==true 
				|| keyH.leftPressed==true || keyH.rightPressed==true) {
		if(keyH.upPressed==true)
		{
			direction="up";
			
			
		}
		else if(keyH.downPressed==true)
		{	
			direction="down";
			
		}
		else if(keyH.rightPressed==true)
		{	
			direction="right";
		
		}
		else if(keyH.leftPressed==true)
		{	
			direction="left";
		
		}
		//CHECK TILE COLLISON
		collisionOn=false;
		gp.cChecker.checkTile(this);
		//gp.cChecker.checkEntity(this,gp.npc);
		//CHECK OBJECT COLLISION
		int objIndex=gp.cChecker.checkObject(this, true);
		pickUpObject(objIndex);
		int npcIndex=gp.cChecker.checkEntity(this,gp.npc);
		interact(npcIndex);
		//if collision is false,player can move
		if(collisionOn==false) {
			switch(direction) {
			case "up":
				worldY-=speed;
				break;
			case "down":
				worldY+=speed;
				break;
			case "left":
				worldX-=speed;
				break;
			case "right":
				worldX+=speed;
				break;
			}
		}
		spriteCounter++;
		if(spriteCounter>10)
		{
			if(spriteNum==1)
				spriteNum=2;
		else if(spriteNum==2)
			spriteNum=1;
		spriteCounter=0;
		}
		}
	}
	public void pickUpObject(int i) {
		
		if(i!=999) {
			 String objectName=gp.obj[i].name;
			 switch(objectName) {
			 case "Key":
				 gp.obj[i]=null;
				 break;

			 case "Boots":
				 	gp.obj[i]=null;
				 	this.speed+=2;
				 	break;
			 case "Chest":
				 gp.ui.gameFinished=true;
				 gp.stopMusic();
				 //gp.playSE(4);
				 break;
				 //xem lai video 10
				 }
			 
			 
		}
	}
	public void interact(int index){
		
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
		int x=screenX;
		int y=screenY;
		if(screenX>worldX) {
			x=worldX;}
		if(screenY>worldY) {
			y=worldY;
		}
		
		int rightOffset=gp.screenWidth-screenX;
		 if(rightOffset>gp.worldWidth-worldX) {
			 x=gp.screenWidth-(gp.worldWidth-worldX);
		 }
		int bottomOffset=gp.screenHeight-screenY;
			if(bottomOffset>gp.worldHeight-worldY) {
				y=gp.screenHeight- (gp.worldHeight-worldY);
		 }
		g2.drawImage(image,x,y,null);
	}
}

