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
import object.OBJ_Fireball;
public class Player extends Entity{

	KeyHandler keyH;
	public final int screenX,screenY;
	public ArrayList<Entity> inventory=new ArrayList<>();
	public final int maxInventorySize=20;
	public boolean[] mission = new boolean[10];
	public int currentMission=0;
	public int npcIndex;
	public int apple=0;
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
		setMission();
		setDialogue();
		setItems();
	}
	public void setDefaultValues() {
		worldX=gp.tileSize*10;
		worldY=gp.tileSize*7;
		defaultSpeed = 10;
		//set character in the center
		speed=defaultSpeed;
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
		projectile = new OBJ_Fireball(gp);
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
	public void setMission() {
		for(int i=0;i<10;i++) {
			mission[i]=false;
		}
	}
	public void setDialogue() {
		dialogue[0]="Where am i?";
//		dialogue[1]="Where is this???";
//		dialogue[2]="Where can I find him?";
//		
		
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
		//gp.cChecker.checkTile(this);
		
		//CHECK OBJECT COLLISION
		int objIndex=gp.cChecker.checkObject(this, true);
		pickUpObject(objIndex);
		npcIndex=gp.cChecker.checkEntity(this,gp.npc);
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
		if(gp.keyH.shotKeyPressed == true && projectile.alive == false){
			projectile.set(worldX, worldY, direction, true, this);

		// add it to the list
		gp.projectileList.add(projectile);
		}
		//THIS NEEDS TO BE OUTSIDE OF KEY IF STATEMENT(DANG)
		if(invincible == true){
			invincibleCounter++;
			if(invincibleCounter>60){
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if(shotAvailableCounter < 30){
			shotAvailableCounter ++;
		}
		// het bo sung
		// bo sung (Dang)
		if(life > maxLife){
			life = maxLife;
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
			damageMonster(monsterIndex, attack, 3);

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
		// het bo sung
	}
	public void pickUpObject(int i) {
		
		if(i!=999) {
			 
			String objectName=gp.obj[gp.currentMap][i].name;
			 switch(objectName) {
			 case "Key":
				 break;
			 case "Heart":
				 this.life+=1;
			 case "Boots":
				 //	this.speed+=2;
				 	break;
			 case "Apple":
				 apple++;
				 if(apple==5) currentMission++;
				// gp.stopMusic();
				 //gp.playSE(4);
				 break;
			 case "Entry_Cave":
				 worldX=15*gp.tileSize;
				 worldY=29*gp.tileSize;
				 gp.currentMap=1;
				 //xem lai video 10
				 }
			 //them code o day dong 323-334
			 String text ;
			 if(inventory.size() != maxInventorySize) {
				 inventory.add(gp.obj[gp.currentMap][i]);
				 text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
				 }
			 else {
				 text = "You cannot carry any more!";
			 }
			 gp.ui.addMessage(text);
			// gp.obj[gp.currentMap][i] = null;
				 }
	}
	public void interact(int i){
		if(gp.keyH.enterPressed == true){
			if(i != 999){
				gp.gameState = gp.dialogueState;
				gp.ui.drawDialogueScreen();
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
	public void damageMonster(int i, int attack, int knockBackPower){
		if(i != 999){
			if(gp.monster[gp.currentMap][i].invincible == false){

				if(knockBackPower >0){
					knockBack(gp.monster[gp.currentMap][i], knockBackPower);
				}

				gp.monster[gp.currentMap][i].life -= 1;
				gp.monster[gp.currentMap][i].invincible = true;
				gp.monster[gp.currentMap][i].damageReaction();
				if(gp.monster[gp.currentMap][i].life <= 0){
					gp.monster[gp.currentMap][i].dying = true;
				}
			}
		}	
	}

	public void knockBack (Entity entity, int knockBackPower){
		entity.direction = direction;
		entity.speed += knockBackPower;
		entity.knockBack = true;

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

}

