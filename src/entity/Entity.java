package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;
public abstract class Entity {
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
	public int shotAvailableCounter = 0;
	public int width;
	public int height;
	public boolean alive=true;
	public boolean dying=false;
	public int dyingcounter;
	boolean hpBarOn = false;
	int hpBarCounter = 0;
	public boolean knockBack = false;
	int knockBackCounter = 0;
	public int defaultSpeed;
	public int knockBackPower = 0;
	public int numberDialogue;
	public String[][] dialogue = new String[10][20];
	//10-maxMission 20-dialogue per mission
	public int type;//0-player;1-npc;2-monster;
	final int playerType=0;
	int monsterType=1;
	int guardType=2;
	int	headManType=3;
	public final int swordType = 4;//them dong code nay
	public final int arrowType = 5;//them dong code nay
	public int attack;
	public Entity currentWeapon;
	public int type_pickupOnly = 7;
	public Projectile projectile;

	public ArrayList<Entity> inventory = new ArrayList<>();
	public int amount = 0;
	public boolean stackable = false;
	public String description = "";
	public int attackValue;
	public Entity(GamePanel gp) {
		this.gp=gp;
	}
	
	public void setAction() {	
	};
	public void damageReaction(){

	}
	public void speak(){}
	public void update() {
		// bo sung knockBack(Dang)
		 if(knockBack == true){
			collisionOn=false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.monster); // checking collision between Entities(DANG)
		boolean contactPlayer = gp.cChecker.checkPlayer(this);

		if(this.type == monsterType && contactPlayer == true){
			damagePlayer(attack);
			
		}
		if(collisionOn == true){
			knockBackCounter = 0;
			knockBack = false;
			speed = defaultSpeed;
		}
		else if(collisionOn == false){
			switch(gp.player.direction){
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
		
		knockBackCounter ++;
		if(knockBackCounter == 10){
			knockBackCounter = 0;
			knockBack = false;
			speed = defaultSpeed;
		}
		 }
		 else{
			setAction();
	
		collisionOn=false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.monster); // checking collision between Entities(DANG)
		boolean contactPlayer = gp.cChecker.checkPlayer(this);

		if(this.type == monsterType && contactPlayer == true){
			damagePlayer(attack);
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
		}
		// het bo sung knockBack(Dang)
		
		
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
		// bo sung projectile(Dang)
		if(shotAvailableCounter < 30){
			shotAvailableCounter ++;
		}
		// het bo sung
	}
	public void damagePlayer(int attack){
		if(gp.player.invincible == false){
			//we can give damage
			gp.player.life -= 1;
			gp.player.invincible = true;
		}
	}
	public void use(Entity entity) {
	}
	public void checkDrop(){
	}
	public void dropItem(Entity droppedItem,int x,int y){
		for(int i = 0; i< gp.obj[0].length; i++){
			if(gp.obj[gp.currentMap][i] == null){
				gp.obj[gp.currentMap][i] = droppedItem; 
				gp.obj[gp.currentMap][i].worldX = x; // the dead monster's worldX
				gp.obj[gp.currentMap][i].worldY = y;
				break;
			} 
		}
	}
	public static Object get(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	public BufferedImage setup(String imagePath,int width,int height) {
		UtilityTool uTool=new UtilityTool();
		this.width=width;
		this.height=height;
		BufferedImage image=null;
		try {
			image=ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
			image=uTool.scaledImage(image, width, height);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
		
	}

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
		int rightOffset=gp.SCREEN_WIDTH-gp.player.screenX;
		 if(rightOffset>gp.worldWidth-gp.player.worldX) {
			 screenX=gp.SCREEN_WIDTH-(gp.worldWidth-worldX);
		 }
		int bottomOffset=gp.SCREEN_HEIGHT-gp.player.screenY;
			if(bottomOffset>gp.worldHeight-gp.player.worldY) {
				screenY=gp.SCREEN_HEIGHT- (gp.worldHeight-worldY);
		 }		
		 // THEM TU DONG  THANH MAU CUA CON QUAI(DANG)
		 if(type == monsterType && hpBarOn == true ){
			double oneScale = (double)gp.TILE_SIZE/maxLife;
			double hpBarValue = oneScale*life;

			g2.setColor(new Color(35, 35, 35));
			g2.fillRect(screenX -1, screenY - 16, gp.TILE_SIZE +2, 12);
			
			g2.setColor(new Color(255,0,30));
			g2.fillRect(screenX , screenY - 15, (int)hpBarValue, 10);
			
			hpBarCounter++;

			if(hpBarCounter > 600){
				hpBarCounter = 0;
				hpBarOn = false;
			}
		}	
		
		g2.drawImage(image,screenX,screenY,width,height,null); 
	
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
