package entity;
//import java.io.IOException;
import java.awt.AlphaComposite;
//import java.awt.Color;
//import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.*;
//import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
//import main.UtilityTool;
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
		getPlayerAttackImage();
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
		up1=setup("/player/Up1", gp.tileSize, gp.tileSize);
		up2=setup("/player/Up2", gp.tileSize, gp.tileSize);
		down1=setup("/player/Down1", gp.tileSize, gp.tileSize);
		down2=setup("/player/Down2", gp.tileSize, gp.tileSize);
		left1=setup("/player/Left1", gp.tileSize, gp.tileSize);
		left2=setup("/player/Left2", gp.tileSize, gp.tileSize);
		right1=setup("/player/Right1", gp.tileSize, gp.tileSize);
		right2=setup("/player/Right2", gp.tileSize, gp.tileSize);

		//up1=setup("boy_up_1", gp.tileSize, gp.tileSize);
		//up2=setup("boy_up_2", gp.tileSize, gp.tileSize);
		//down1=setup("boy_down_1", gp.tileSize, gp.tileSize);
		//down2=setup("boy_down_2", gp.tileSize, gp.tileSize);
		//left1=setup("boy_left_1", gp.tileSize, gp.tileSize);
		//left2=setup("boy_left_2", gp.tileSize, gp.tileSize);
		//right1=setup("boy_right_1", gp.tileSize, gp.tileSize);
		//right2=setup("boy_right_2", gp.tileSize, gp.tileSize);
	}
	public void getPlayerAttackImage(){
		attackup1=setup("/player/Up1", gp.tileSize, gp.tileSize*2);
		attackup2=setup("/player/Up2", gp.tileSize, gp.tileSize*2);
		attackdown1=setup("/player/Down1", gp.tileSize, gp.tileSize*2);
		attackdown2=setup("/player/Down2", gp.tileSize, gp.tileSize*2);
		attackleft1=setup("/player/Left1", gp.tileSize*2, gp.tileSize);
		attackleft2=setup("/player/Left2", gp.tileSize*2, gp.tileSize);
		attackright1=setup("/player/Right1", gp.tileSize*2, gp.tileSize);
		attackright2=setup("/player/Ringt2", gp.tileSize*2, gp.tileSize);
	}
	
	public void update() {
		if(attacking == true){
			attacking();
		}
		
		else if(keyH.upPressed==true || keyH.downPressed==true 
				|| keyH.leftPressed==true || keyH.rightPressed==true || keyH.enterPressed==true) {
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
		// CHECK MONSTER COLLISION
		int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
		contactMonster(monsterIndex);

		// CHECK EVENT
		//gp.eHandler.checkEvent();

		
		//if collision is false,player can move
		if(collisionOn==false && keyH.enterPressed == false) {
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
		gp.keyH.enterPressed = false;

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
		//THIS NEEDS TO BE OUTSIDE OF KEY IF STATEMENT
		if(invincible == true){
			invincibleCounter++;
			if(invincibleCounter>60){
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}
	public void attacking (){
		spriteCounter++;

		if(spriteCounter<=5){
			spriteCounter = 1;
		}
		if(spriteCounter>5 && spriteCounter <=25){
			spriteNum = 2;
		}
		if(spriteCounter>25){
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
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
	
	public void contactMonster(int i){
		if(i !=999){
			if(invincible == false){
				life-=1;
			invincible = true;	
			}
		}
	}	
	public void draw(Graphics2D g2) {
		BufferedImage image=null;
		switch(direction) {
		case "up":
			if (attacking == false){
				if(spriteNum==1) image=up1;
				if(spriteNum==2) image=up2;
			break;
			}
			if (attacking == true){
				if(spriteNum==1) image=attackup1;
				if(spriteNum==2) image=attackup2;
			break;
			}
			
		case "down":
			if (attacking == false){	
				if(spriteNum==1) image=down1;
				if(spriteNum==2) image=down2;
			break;
			}
			if (attacking == true){	
				if(spriteNum==1) image=attackdown1;
				if(spriteNum==2) image=attackdown2;
			break;
			}
		case "left":
			if (attacking == false){
				if(spriteNum==1) image=left1;
				if(spriteNum==2) image=left2;
			break;
			}
			if (attacking == true){	
				if(spriteNum==1) image=attackleft1;
				if(spriteNum==2) image=attackleft2;
			break;
			}
		case "right":
			if (attacking == false){
				if(spriteNum==1) image=right1;
				if(spriteNum==2) image=right2;
			break;
			}
			if (attacking == true){	
				if(spriteNum==1) image=attackright1;
				if(spriteNum==2) image=attackright2;
			break;
			}
		if(invincible == true){
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
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
		// Reset alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		//DEBUG
		//g2.setFont(new Font("Arial", Font.PLAIN, 26));
		//g2.setColor(Color.white);
		//g2.drawString("Invincible:"+invincibleCounter, 10, 400);
	}
}
}


