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


import object.OBJ_Tooth;
import object.OBJ_Arrow;
import object.OBJ_Bow;
import object.OBJ_Sword;
import object.OBJ_Apple;
import object.OBJ_Arrow;

public class Player extends Entity{

	KeyHandler keyH;
	public final int screenX,screenY;
	//public ArrayList<Entity> inventory=new ArrayList<>();
	public final int maxInventorySize=20;
	public boolean[] mission = new boolean[10];
	public int currentMission=0;
	public int npcIndex;
	public int strength;
	public double hp;//them dong code nay
	public double maxHp = 10;//them dong code nay
	public Player(GamePanel gp,KeyHandler keyH)
	{
		super(gp); 
		this.keyH=keyH;
		collision=true;
		screenX=gp.SCREEN_WIDTH/2-(gp.TILE_SIZE/2);
		screenY=gp.SCREEN_HEIGHT/2-(gp.TILE_SIZE/2);
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
		setDialogue();
		setItems();
	}
	
	public void setDefaultValues() {
		worldX=gp.TILE_SIZE*10;
		worldY=gp.TILE_SIZE*7;
		defaultSpeed = 10;
		//set character in the center
		speed=defaultSpeed;
		direction="down";
		maxHp = 10;
		hp = maxHp;//them dong code nay
		maxLife=3;//them dong code nay
		life=maxLife;
		projectile = new OBJ_Arrow(gp) ;
		currentWeapon = new OBJ_Sword(gp);//them doan code nay
		
	}
	
	public void setItems() {
	}
	
	public void getPlayerImage() {
		up1=setup("/player/Up1",gp.TILE_SIZE,gp.TILE_SIZE);
		up2=setup("/player/Up2",gp.TILE_SIZE,gp.TILE_SIZE);
		down1=setup("/player/Down1",gp.TILE_SIZE,gp.TILE_SIZE);
		down2=setup("/player/Down2",gp.TILE_SIZE,gp.TILE_SIZE);
		left1=setup("/player/Left1",gp.TILE_SIZE,gp.TILE_SIZE);
		left2=setup("/player/Left2",gp.TILE_SIZE,gp.TILE_SIZE);
		right1=setup("/player/Right1",gp.TILE_SIZE,gp.TILE_SIZE);
		right2=setup("/player/Right2",gp.TILE_SIZE,gp.TILE_SIZE);
	}
	public void getPlayerAttackImage() {
		attackup1=setup("/player/boy_attack_up_1", gp.TILE_SIZE, gp.TILE_SIZE*2);
		attackup2=setup("/player/boy_attack_up_2", gp.TILE_SIZE, gp.TILE_SIZE*2);
		attackdown1=setup("/player/boy_attack_down_1", gp.TILE_SIZE, gp.TILE_SIZE*2);
		attackdown2=setup("/player/boy_attack_down_2", gp.TILE_SIZE, gp.TILE_SIZE*2);
		attackleft1=setup("/player/boy_attack_left_1", gp.TILE_SIZE*2, gp.TILE_SIZE);
		attackleft2=setup("/player/boy_attack_left_2", gp.TILE_SIZE*2, gp.TILE_SIZE);
		attackright1=setup("/player/boy_attack_right_1", gp.TILE_SIZE*2, gp.TILE_SIZE);
		attackright2=setup("/player/boy_attack_right_2", gp.TILE_SIZE*2, gp.TILE_SIZE);
	}
	
	public void setDialogue() {
		dialogue[0][0]="Where am i?";	
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
		
		//CHECK OBJECT COLLISION
		int objIndex=gp.cChecker.checkObject(this, true);
		pickUpObject(objIndex);
		npcIndex=gp.cChecker.checkEntity(this,gp.npc);
		interact(npcIndex);
		//System.out.println("INdex la "+npcIndex);
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
		if(hp > maxHp) {
			hp = maxHp;
		}
		if (hp == 0) {
			if(life > 0) {
				life --;
				hp = maxHp;
			}
			else {
				gp.gameState = gp.gameOverState;
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
			 case "Heart":

				 if(canObtainItem(gp.obj[gp.currentMap][i])==true);
				 gp.obj[gp.currentMap][i]=null;
				 this.life+=1;
				 break;
			 case "Boots":
//				 System.out.println(objectName+"Boots");
//				 if(canObtainItem(gp.obj[gp.currentMap][i])==true);
//				 gp.obj[gp.currentMap][i]=null;
//
//				 this.speed+=2;
				 	break;
			 case "Apple":
				 if(canObtainItem(gp.obj[gp.currentMap][i])==true);
				 gp.obj[gp.currentMap][i]=null;
				 OBJ_Apple.numberCollected++;
				 //System.out.println(OBJ_Apple.numberCollected);
				 if(OBJ_Apple.numberCollected==4) 
					 {
					 currentMission=2;
					 }
				 for(int j=0;j<gp.npc[gp.currentMap].length;j++)
				if( gp.npc[gp.currentMap][j]!=null)
				 gp.npc[gp.currentMap][j].numberDialogue=0;
				 //gp.playSE(4);
				 break;
			 case "Tooth":
				 if(canObtainItem(gp.obj[gp.currentMap][i])==true);
				 gp.obj[gp.currentMap][i]=null;
				 OBJ_Tooth.numberCollected++;
				 if(OBJ_Tooth.numberCollected==5) currentMission=3;
				 for(int j=0;j<gp.npc[gp.currentMap].length;j++)
					 if( gp.npc[gp.currentMap][j]!=null)
					 gp.npc[gp.currentMap][j].numberDialogue=0;
				 break;
			 case "Sword":
				 if(canObtainItem(gp.obj[gp.currentMap][i])==true);
				 gp.obj[gp.currentMap][i]=null;
				 currentWeapon = new OBJ_Sword(gp);
				 attack = currentWeapon.attack;
				 break; 
			
			 case "Bow":
				 if(canObtainItem(gp.obj[gp.currentMap][i])==true);
				 gp.obj[gp.currentMap][i]=null;
				 break; 
			 case "Entry_Cave":
				 if(gp.player.currentMission>=3) {
				 if(gp.currentMap==0) {
				 worldX=3*gp.TILE_SIZE;
				 worldY=39*gp.TILE_SIZE;
				 gp.currentMap=1;
				 }
				 else {
					 worldX=15*gp.TILE_SIZE;
					 worldY=43*gp.TILE_SIZE;
					 gp.currentMap=0;
					 
				 }
				 }
				 //xem lai video 10
				 break;
				 }
			 //them code o day dong 323-334
			 
			// gp.obj[gp.currentMap][i] = null;
				 }
	}
	public void interact(int i){
		if(gp.keyH.enterPressed == true){
			if(i != 999){
				gp.gameState = gp.dialogueState;
				//System.out.println("truoc khi tuong tac : "+i);
				gp.ui.drawDialogueScreen(i);
			}
			else{
				attacking = true;
			}
		}
	}
	public void contactMonster(int i){
		if(i !=999){
			if(invincible == false){
				hp-=1;
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

				gp.monster[gp.currentMap][i].life -= attack;
				gp.monster[gp.currentMap][i].invincible = true;
				gp.monster[gp.currentMap][i].damageReaction();
				if(gp.monster[gp.currentMap][i].life <= 0){
					gp.monster[gp.currentMap][i].dying = true;
				}
			}
		}	
	}
	public int searchItemInInventory(String itemName) {
		int itemIndex = 999;

		for(int i=0;i < gp.player.inventory.size();i++) {
			if(gp.player.inventory.get(i).name.equals(itemName)) {
				itemIndex = i;
				break;
			}
		}
		return itemIndex;
	}
	public boolean canObtainItem(Entity item) {

		boolean canObtain = true;

		//CHECK IF STACKABLE
		if(item.stackable == true) {
			int index = searchItemInInventory(item.name);

			if(index != 999) {
			if(inventory.get(index).amount==0) inventory.get(index).amount++;
			
		inventory.get(index).amount++;
				canObtain = true;
			}
			else {//New item so need to check vacancy
					if(inventory.size() != maxInventorySize) {
						inventory.add(item);
						canObtain = true;
					}
		       }

	}
		else {//NOT STACKABLE so check vacancy 
			if(inventory.size() != maxInventorySize) {

				canObtain = false;
			}
		}
		return canObtain;
	}
	public void knockBack (Entity entity, int knockBackPower){
		entity.direction = direction;
		entity.speed += knockBackPower;
		entity.knockBack = true;

	}
	
	//them code o day dong 405-418
	public void selectItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot();
		
		if (itemIndex < gp.player.inventory.size()) {
			Entity selectedItem = gp.player.inventory.get(itemIndex);
			
			if (selectedItem.type == swordType ) {
				
				currentWeapon = selectedItem;
				
				getPlayerAttackImage();
			}
			if(selectedItem.type == arrowType) {
				projectile = (Projectile) selectedItem;
				getPlayerAttackImage();
			}
		}
	}//them code den day dong 418
	

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
				tempScreenY = screenY - gp.TILE_SIZE;
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
				tempScreenX = screenX - gp.TILE_SIZE;
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
			if(attacking==true && direction=="left") tempScreenX=worldX-gp.TILE_SIZE;
			else tempScreenX=worldX;}
		if(screenY>worldY) {
			if(attacking==true && direction=="up") tempScreenY=worldY-gp.TILE_SIZE;
			else tempScreenY=worldY;
		}
		
		int rightOffset=gp.SCREEN_WIDTH-screenX;
		 if(rightOffset>gp.worldWidth-worldX) {
			 
			 
			 tempScreenX=gp.SCREEN_WIDTH-(gp.worldWidth-worldX);
			 if(attacking==true && direction=="left") tempScreenX-=gp.TILE_SIZE;
			
		 }
		int bottomOffset=gp.SCREEN_HEIGHT-screenY;
			if(bottomOffset>gp.worldHeight-worldY) {
				tempScreenY=gp.SCREEN_HEIGHT- (gp.worldHeight-worldY);
				if(attacking==true && direction=="up") tempScreenY-=gp.TILE_SIZE;
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

