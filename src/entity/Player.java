package entity;
import java.io.IOException;
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
		//BO SUNG 36-37(DANG)
		attackArea.width = 36;
		attackArea.height = 36;
		//HET BO SUNG
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
		up1=setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
		up2=setup("/player/boy_up_2", gp.tileSize, gp.tileSize);
		down1=setup("/player/boy_down_1", gp.tileSize, gp.tileSize);
		down2=setup("/player/boy_down_2", gp.tileSize, gp.tileSize);
		left1=setup("/player/boy_left_1", gp.tileSize, gp.tileSize);
		left2=setup("/player/boy_left_2", gp.tileSize, gp.tileSize);
		right1=setup("/player/boy_right_1", gp.tileSize, gp.tileSize);
		right2=setup("/player/boy_right_2", gp.tileSize, gp.tileSize);
	}
	//THEM TU DONG 66-75 ANH PLAYER KHI TAN CONG(ATTACKING)
	
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
	// HET DONG 75
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
				 gp.stopMusic();
				 //gp.playSE(4);
				 break;
				 //xem lai video 10
				 }
			 
			 
		}
	}
	// THEM TU DONG 232-242 HIEU UNG BAM ENTER=ATTACK
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
	// ket thuc bo sung
	
	// player receives damage 243-250(DANG)
	public void contactMonster(int i){
		if(i !=999){
			if(invincible == false){
				life-=1;
			invincible = true;	
			}
		}
	}	
	// ket thuc bo sung

	// bo sung give damage and kill monster 254-263(DANG)
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
	// het bo sung
	public void draw(Graphics2D g2) {
		BufferedImage image=null;
		int tempscreenX= screenX;
		int tempscreenY= screenY;
		switch(direction) {
		case "up":
			if (attacking == false){
				if(spriteNum==1) image=up1;
				if(spriteNum==2) image=up2;
			break;
			}
			if (attacking == true){
				//BO SUNG 244(DANG)
				tempscreenY = screenY - gp.tileSize;
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
				tempscreenX = screenX - gp.tileSize;
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
		g2.drawImage(image,tempscreenX, tempscreenY, null);
		// Reset alpha(DANG)
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		//DEBUG(DANG)
		g2.setFont(new Font("Arial", Font.PLAIN, 26));
		g2.setColor(Color.white);
		g2.drawString("Invincible:"+invincibleCounter, 10, 400);

}
}


