package entity;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;
public class Entity {
	GamePanel gp;
	public int worldX,worldY;	
	public int speed;
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	public BufferedImage attackup1, attackup2, attackdown1, attackdown2, attackleft1, attackleft2, attackright1, attackright2;
	public String direction="down";
	public int spriteCounter=0;
	public int spriteNum=1;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public BufferedImage image,image2,image3;
	public String name;
	public Rectangle attackArea = new Rectangle(0,0,0,0);
	public int solidAreaDefaultX,solidAreaDefaultY;
	public boolean collisionOn=false;
	public int maxLife;
	public int life;
	public int actionLockCounter=0;
	public boolean invincible=false;
	public int invincibleCounter = 0;//  creating invincible time(DANG)
	boolean attacking = false;
	public boolean collision;
	
	public boolean alive=true;
	public boolean dying=false;
	public int dyingcounter;
	boolean hpBarOn = false;
	int hpBarCounter = 0;
	
	public int type;//0-player;1-npc;2-monster;
	public int level;
	public int strength;
	public int dexterity;
	public int attack;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public Entity currentWeapon;
	public Entity currentShield; 
	public int attackValue;
	public int defenseValue;
	public String description = "";
	public Entity(GamePanel gp) {
		this.gp=gp;
	}
	
	public void setAction() {
		
	};
	public void damageReaction(){

	}
	public void speak(){}
	public void update() {
		setAction();
		
		collisionOn=false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.monster); // checking collision between Entities(DANG)
		boolean contactPlayer = gp.cChecker.checkPlayer(this);

		if(this.type == 2 && contactPlayer == true){
			if(gp.player.invincible == false){
				//we can give damage
				gp.player.life -= 1;
				gp.player.invincible = true;
			}
		}
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
		if(spriteCounter>12)
		{
			if(spriteNum==1)
				spriteNum=2;
		else if(spriteNum==2)
			spriteNum=1;
		spriteCounter=0;
		}
		if(invincible == true){
			invincibleCounter++;
			if(invincibleCounter>40){
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}
//	public void update() {
//		setAction();
//		
//		collisionOn=false;
//		gp.cChecker.checkTile(this);
//		gp.cChecker.checkObject(this, false);
//		gp.cChecker.checkPlayer(this);
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
//		if(spriteCounter>15)
//		{
//			if(spriteNum==1)
//				spriteNum=2;
//		else if(spriteNum==2)
//			spriteNum=1;
//		spriteCounter=0;
//		}
//		
//	}
	
	public BufferedImage setup(String imagePath,int width,int height) {
		UtilityTool uTool=new UtilityTool();
		BufferedImage image=null;
		try {
			image=ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
			image=uTool.scaledImage(image, width, height);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
		
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
//		int screenX=worldX-gp.player.worldX+gp.player.screenX;
//		int screenY=worldY-gp.player.worldY+gp.player.screenY;
//		if(gp.player.screenX>gp.player.worldX) {
//			screenX=worldX;
//		}
//		if(gp.player.screenY>gp.player.worldY) {
//			screenY=worldY;
//		}
//		int rightOffset=gp.screenWidth-gp.player.screenX;
//		 if(rightOffset>gp.worldWidth-gp.player.worldX) {
//			 screenX=gp.screenWidth-(gp.worldWidth-worldX);
//		 }
//		int bottomOffset=gp.screenHeight-gp.player.screenY;
//			if(bottomOffset>gp.worldHeight-gp.player.worldY) {
//				screenY=gp.screenHeight- (gp.worldHeight-worldY);
//		 }		
//		else
//		g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null); 
//}
	public void draw(Graphics2D g2) {
		BufferedImage image=null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldX + gp.player.screenY;
			
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

		if(invincible == true){
			hpBarOn = true;
			hpBarCounter = 0;
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
		int rightOffset=gp.screenWidth-gp.player.screenX;
		 if(rightOffset>gp.worldWidth-gp.player.worldX) {
			 screenX=gp.screenWidth-(gp.worldWidth-worldX);
		 }
		int bottomOffset=gp.screenHeight-gp.player.screenY;
			if(bottomOffset>gp.worldHeight-gp.player.worldY) {
				screenY=gp.screenHeight- (gp.worldHeight-worldY);
		 }		
		 // THEM TU DONG  THANH MAU CUA CON QUAI(DANG)
		 if(type == 2 && hpBarOn == true ){
			double oneScale = (double)gp.tileSize/maxLife;
			double hpBarValue = oneScale*life;

			g2.setColor(new Color(35, 35, 35));
			g2.fillRect(screenX -1, screenY - 16, gp.tileSize +2, 12);
			
			g2.setColor(new Color(255,0,30));
			g2.fillRect(screenX , screenY - 15, (int)hpBarValue, 10);
			
			hpBarCounter++;

			if(hpBarCounter > 600){
				hpBarCounter = 0;
				hpBarOn = false;
			}
		}	
		
		g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null); 
	
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		changeAlpha(g2,1F);
		// HET BO SUNG(DANG)
	// bo sung hieu ung quai chet di tu 194-231(DANG)
	}
	public void dyingAnimation(Graphics2D g2){
		dyingcounter++;
		
		int i =5;

		if(dyingcounter <= i){	
			changeAlpha(g2, 0f);
		}
		if(dyingcounter > i && dyingcounter <= i*2){
			changeAlpha(g2, 1f);
		}
		if(dyingcounter > i*2 && dyingcounter <= i*3){
			changeAlpha(g2, 0f);
		}
		if(dyingcounter > i*3 && dyingcounter <= i*4){
			changeAlpha(g2, 1f);
		}
		if(dyingcounter > i*4 && dyingcounter <= i*5){
			changeAlpha(g2, 0f);
		}
		if(dyingcounter > i*5 && dyingcounter <= i*6){
			changeAlpha(g2, 1f);
		}
		if(dyingcounter > i*6 && dyingcounter <= i*7){
			changeAlpha(g2, 0f);
		}
		if(dyingcounter > i*7 && dyingcounter <=i*8){
			changeAlpha(g2, 1f);
		}
		if(dyingcounter >i*8){
			dying = false;
			alive = false;
		}
	}
	public void changeAlpha(Graphics2D g2, float alphaValue){
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));	
	}
	}
