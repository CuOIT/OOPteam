package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
import object.Object;

public class Monster extends Entity{
	
	public int attackLockCounter;
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	public boolean alive=true;
	public boolean dying=false;
	public int attack;
	public int normalSpeed;
	public int dyingcounter;
	public Monster(GamePanel gp) {
		super(gp);
	}
	
	public void update() {
		// bo sung knockBack(Dang)
		 if(knockBack == true){
			collisionOn=false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.monster); // checking collision between Entities(DANG)
		boolean contactPlayer = gp.cChecker.checkPlayer(this);

		if(contactPlayer == true){
			damagePlayer(attack);
			
		}
		if(collisionOn == true){
			knockBackCounter = 0;
			knockBack = false;
			
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
		if(knockBackCounter >= 10){
			speed=normalSpeed;
			knockBackCounter = 0;
			knockBack = false;
		
		}
		 }
		 else{
			setAction();
	
		collisionOn=false;
		gp.cChecker.checkTile(this);
		

		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.monster); // checking collision between Entities(DANG)
		boolean contactPlayer = gp.cChecker.checkPlayer(this);

		if(contactPlayer == true){
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
		if(takingDamage == true){
			takingDamageCounter++;
			if(takingDamageCounter>20){
				takingDamage = false;
				takingDamageCounter = 0;
			}	
		}	
			attackLockCounter ++;
	
	}
    public void damageReaction(){
	actionLockCounter = 0;
	direction = gp.player.direction;

}	
    public double distanceToPlayer(){
    	return Math.sqrt(Math.pow(this.worldX-gp.player.worldX,2)+Math.pow(this.worldY-gp.player.worldY,2));
    }
    public void pathFinding() {
    	if(Math.abs(this.worldY-gp.player.worldY)>10) {
    	if(this.worldY<gp.player.worldY) direction="down";
    	else direction="up";
    	}
    	else if(this.worldX<gp.player.worldX) direction="right";
    	else direction="left";
    	
    }
	public void damagePlayer(int attack){
		if(gp.player.takingDamage == false){
			//we can give damage
			gp.player.hp -= attack;
			gp.player.takingDamage = true;
		}
	}
	public void dropItem(Object droppedItem){
		for(int i = 0; i< gp.obj[0].length; i++){
			if(gp.obj[gp.currentMap][i] == null){
				gp.obj[gp.currentMap][i] = droppedItem; 
				gp.obj[gp.currentMap][i].worldX = worldX;
				gp.obj[gp.currentMap][i].worldY = worldY;
				break;
			} 
		}
	}
	public void draw(Graphics2D g2) {
		BufferedImage image=null;
		screenX = worldX - gp.player.worldX + gp.player.screenX;
		screenY = worldY - gp.player.worldX + gp.player.screenY;
			
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
		case "upleft":
			if(spriteNum==1)
			image=up1;
			if(spriteNum==2)
				image=up2;
		case "upright":
			if(spriteNum==1)
			image=up1;
			if(spriteNum==2)
				image=up2;
		case "downleft":
			if(spriteNum==1)
			image=up1;
			if(spriteNum==2)
				image=up2;
		case "downright":
			if(spriteNum==1)
			image=up1;
			if(spriteNum==2)
				image=up2;
		}

		if(takingDamage == true){
			
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
			double oneScale = (double)gp.TILE_SIZE/maxLife;
			double hpBarValue = oneScale*life;
			g2.setColor(new Color(35, 35, 35));
			g2.fillRect(screenX -1, screenY - 16, gp.TILE_SIZE +2, 12);
			
			g2.setColor(new Color(255,0,30));
			g2.fillRect(screenX , screenY - 15, (int)hpBarValue, 10);
			
	
		
		g2.drawImage(image,screenX,screenY,width,height,null); 
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		changeAlpha(g2,1F);

	}
	public void checkDrop() {
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
public void setAction() {
	//LOAD ACTION OF MONSTER
}
}

