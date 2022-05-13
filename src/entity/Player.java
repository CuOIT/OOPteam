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
	GamePanel gp;
	KeyHandler keyH;
	public final int screenX,screenY;
	public int hasKey=0;
	public Player(GamePanel gp,KeyHandler keyH)
	{
		this.gp = gp;
		this.keyH=keyH;
		
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
		worldX=gp.tileSize*23;
		worldY=gp.tileSize*21;
		//set character in the center
		speed=10;
		direction="down";
		maxLife=10;
		life=10;
	}
	
	public void getPlayerImage() {
		up1=setup("Up1");
		up2=setup("Up2");
		down1=setup("Down1");
		down2=setup("Down2");
		left1=setup("Left1");
		left2=setup("Left2");
		right1=setup("Right1");
		right2=setup("Right2");
	}
	public BufferedImage setup(String imageName) {
		UtilityTool uTool=new UtilityTool();
		BufferedImage image=null;
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/player/"+imageName+".png"));
			image=uTool.scaledImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
		
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
		//CHECK OBJECT COLLISION
		int objIndex=gp.cChecker.checkObject(this, true);
		pickUpObject(objIndex);
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
				 hasKey++;
				 gp.obj[i]=null;
				 break;
			 
			 case "Door":
				 if(hasKey>0)
				 {
					 gp.obj[i]=null;
					 hasKey--;
				 }
					 break;
			 case "Boots":
				 	gp.obj[i]=null;
				 	this.speed+=2;
				 	break;
			 case "chest":
				 gp.ui.gameFinished=true;
				 hasKey--;
				 gp.stopMusic();
				 //gp.playSE(4);
				 break;
				 //xem lai video 10
				 }
			 
			 
		}
	}
	public void draw(Graphics2D g2) {
		//g2.setColor(Color.white);
 		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
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

