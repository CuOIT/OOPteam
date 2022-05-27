package entity;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.*;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
//import object.OBJ_Axe;
//import object.OBJ_Hard_Shileld;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
public class Player extends Entity{

	KeyHandler keyH;
	public final int screenX,screenY;
	public ArrayList<Entity> inventory=new ArrayList<>();
	public final int maxInventorySize=20;
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
		attackArea.width = 36;
		attackArea.height = 36;
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		setItems();
	}
	public void setDefaultValues() {
		worldX=gp.tileSize*15;
		worldY=gp.tileSize*16;
		//set character in the center
		speed=10;
		direction="down";
		maxLife=10;
		life=maxLife;
		strength=1;
		dexterity=1;
		exp=0;
		nextLevelExp=5;
		coin=0;
		currentWeapon=new OBJ_Sword_Normal(gp);
		currentShield=new OBJ_Shield_Wood(gp);
	}
	public void setItems() {
		inventory.add(new OBJ_Sword_Normal(gp));
		inventory.add(new OBJ_Shield_Wood(gp));
	//	inventory.add(new OBJ_Hard_Shileld(gp));
		inventory.add(new OBJ_Key(gp));
	//	inventory.add(new OBJ_Axe(gp));
	}
	public void getPlayerImage() {
		up1=setup("/player/Up1",gp.tileSize,gp.tileSize);
		up2=setup("/player/Up2",gp.tileSize,gp.tileSize);
		down1=setup("/player/Down1",gp.tileSize,gp.tileSize);
		down2=setup("/player/Down2",gp.tileSize,gp.tileSize);
		left1=setup("/player/Left1",gp.tileSize,gp.tileSize);
		left2=setup("/player/Left2",gp.tileSize,gp.tileSize);
		right1=setup("/player/Right1",gp.tileSize,gp.tileSize);
		right2=setup("/player/Right2",gp.tileSize,gp.tileSize);
	}
	public void getPlayerAttackImage() {
		attackup1=setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize*2);
		attackup2=setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize*2);
		attackdown1=setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize*2);
		attackdown2=setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize*2);
		attackleft1=setup("/player/boy_attack_left_1", gp.tileSize*2, gp.tileSize);
		attackleft2=setup("/player/boy_attack_left_2", gp.tileSize*2, gp.tileSize);
		attackright1=setup("/player/boy_attack_right_1", gp.tileSize*2, gp.tileSize);
		attackright2=setup("/player/boy_attack_right_2", gp.tileSize*2, gp.tileSize);
	}
//	public void update() {
//		if(keyH.upPressed==true || keyH.downPressed==true 
//				|| keyH.leftPressed==true || keyH.rightPressed==true|| keyH.enterPressed==true) {
//		if(keyH.upPressed==true)
//		{
//			direction="up";
//			
//			
//		}
//		else if(keyH.downPressed==true)
//		{	
//			direction="down";
//			
//		}
//		else if(keyH.rightPressed==true)
//		{	
//			direction="right";
//		
//		}
//		else if(keyH.leftPressed==true)
//		{	
//			direction="left";
//		
//		}
//		else if(keyH.enterPressed==true) {
//			
//		}
//		//CHECK TILE COLLISON
//		collisionOn=false;
//		gp.cChecker.checkTile(this);
//		//gp.cChecker.checkEntity(this,gp.npc);
//		//CHECK OBJECT COLLISION
//		int objIndex=gp.cChecker.checkObject(this, true);
//		pickUpObject(objIndex);
//		int npcIndex=gp.cChecker.checkEntity(this,gp.npc);
//		interact(npcIndex);
//		//if collision is false,player can move
//		if(collisionOn==false) {
//			switch(direction) {
//			case "up":
//				worldY-=speed;
//				break;
//			case "down":
//				worldY+=speed;
//				break;
//			case "left":
//				worldX-=speed;
//				break;
//			case "right":
//				worldX+=speed;
//				break;
//			}
//		}
//		spriteCounter++;
//		if(spriteCounter>10)
//		{
//			if(spriteNum==1)
//				spriteNum=2;
//		else if(spriteNum==2)
//			spriteNum=1;
//		spriteCounter=0;
//		}
//		}
//	}

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
		
		//CHECK OBJECT COLLISION
		int objIndex=gp.cChecker.checkObject(this, true);
		pickUpObject(objIndex);
		int npcIndex=gp.cChecker.checkEntity(this,gp.npc);
		interact(npcIndex);
		// CHECK MONSTER COLLISION 115-116(DANG)
		int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
		contactMonster(monsterIndex);
		// ket thuc bo sung
		
		
		
		//if collision is false,player can move
		if(collisionOn ==false && keyH.enterPressed == false) {
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
		//THIS NEEDS TO BE OUTSIDE OF KEY IF STATEMENT(DANG)
		if(invincible == true){
			invincibleCounter++;
			if(invincibleCounter>60){
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}
	// bo sung tu 160-202(DANG)
	public void attacking (){
		spriteCounter++;

		if(spriteCounter<=5){
			spriteNum = 1;
		}
		if(spriteCounter>5 && spriteCounter <=25){
			spriteNum = 2;

			// Save the current worldX, worldY, solidArea
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;

			//Adjust player's worldX/Y for the attackArea
			switch(direction){
				case "up": worldY -= attackArea.height; break;
				case "down": worldY += attackArea.height; break;
				case "left": worldX -= attackArea.width; break;
				case "right": worldX += attackArea.width; break;
			}

			// attackarea becomes solidarea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;

			// check monster collision with the updated worldX, worldY, solideArea
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex);

			//After checking collision, restrore the original data
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;

		}
		if(spriteCounter>25){
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
		// ket thuc bo sung
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
				// gp.stopMusic();
				 //gp.playSE(4);
				 break;
				 //xem lai video 10
				 }
			 
			 
		}
	}
	public void interact(int i){
		if(gp.keyH.enterPressed == true){
			if(i != 999){
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
			else{
				attacking = true;
			}
		}
	}
	public void contactMonster(int i){
		if(i !=999){
			if(invincible == false){
				life-=1;
			invincible = true;	
			}
		}
	}	
	public void damageMonster(int i){
		if(i != 999){
			if(gp.monster[i].invincible == false){
				gp.monster[i].life -= 1;
				gp.monster[i].invincible = true;
				gp.monster[i].damageReaction();

				if(gp.monster[i].life <= 0){
					gp.monster[i].dying = true;
				}
			}
		}	
	}
	public void draw(Graphics2D g2) {
		BufferedImage image=null;
		int tempScreenX= screenX;
		int tempScreenY= screenY;
		switch(direction) {
		case "up":
			if (attacking == false){
				if(spriteNum==1) image=up1;
				if(spriteNum==2) image=up2;
			break;
			}
			if (attacking == true){
				//BO SUNG 244(DANG)
				tempScreenY = screenY - gp.tileSize;
				//HET BO SUNG
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
				//BO SUNG 268(DANG)
				tempScreenX = screenX - gp.tileSize;
				//HET BO SUNG
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
		}	
		// BO SUNG 289-291(DANG)
		if(invincible == true){
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		}
		// HET BO SUNG
		if(screenX>worldX) {
			if(attacking==true && direction=="left") tempScreenX=worldX-gp.tileSize;
			else tempScreenX=worldX;}
		if(screenY>worldY) {
			if(attacking==true && direction=="up") tempScreenY=worldY-gp.tileSize;
			else tempScreenY=worldY;
		}
		
		int rightOffset=gp.screenWidth-screenX;
		 if(rightOffset>gp.worldWidth-worldX) {
			 
			 tempScreenX=gp.screenWidth-(gp.worldWidth-worldX);
			 if(attacking==true && direction=="left") tempScreenX-=gp.tileSize;
			
		 }
		int bottomOffset=gp.screenHeight-screenY;
			if(bottomOffset>gp.worldHeight-worldY) {
				tempScreenY=gp.screenHeight- (gp.worldHeight-worldY);
				if(attacking==true && direction=="up") tempScreenY-=gp.tileSize;
		 }
		g2.drawImage(image,tempScreenX, tempScreenY, null);
		// Reset alpha(DANG)
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		//DEBUG(DANG)
		g2.setFont(new Font("Arial", Font.PLAIN, 26));
		g2.setColor(Color.white);
		g2.drawString("Invincible:"+invincibleCounter, 10, 400);

}
//	public void draw(Graphics2D g2) {
//		BufferedImage image=null;
//		switch(direction) {
//		case "up":
//			if(spriteNum==1)
//			image=up1;
//			if(spriteNum==2)
//				image=up2;
//			break;
//		case "down":
//			if(spriteNum==1)
//				image=down1;
//			if(spriteNum==2)
//				image=down2;
//			break;
//		case "left":
//			if(spriteNum==1)
//				image=left1;
//				if(spriteNum==2)
//					image=left2;
//			break;
//		case "right":
//			if(spriteNum==1)
//				image=right1;
//				if(spriteNum==2)
//					image=right2;
//			break;
//		}
//		int x=screenX;
//		int y=screenY;
//		if(screenX>worldX) {
//			x=worldX;}
//		if(screenY>worldY) {
//			y=worldY;
//		}
//		
//		int rightOffset=gp.screenWidth-screenX;
//		 if(rightOffset>gp.worldWidth-worldX) {
//			 x=gp.screenWidth-(gp.worldWidth-worldX);
//		 }
//		int bottomOffset=gp.screenHeight-screenY;
//			if(bottomOffset>gp.worldHeight-worldY) {
//				y=gp.screenHeight- (gp.worldHeight-worldY);
//		 }
//		g2.drawImage(image,x,y,null);
//	}
}

