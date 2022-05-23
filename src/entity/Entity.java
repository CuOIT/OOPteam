package entity;
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
	public int solidAreaDefaultX,solidAreaDefaultY;
	public boolean collisionOn=false;
	public int maxLife;
	public int life;
	public int actionLockCounter=0;
	public boolean invincible=false;
	public int invincibleCounter = 0; 
	boolean attacking = false;
	public BufferedImage image,image2,image3;
	public String name;
	public boolean collision;
	public int type; // 0=player, 1=np1, 2=monster

	public Entity(GamePanel gp) {
		this.gp=gp;
	}
	
	public void setAction() {
		
	};
	public void update() {
		setAction();
		
		collisionOn=false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);

		if(this.type == 2 && contactPlayer == true){
			if(gp.player.invincible == false){
				//we can give damage
				gp.player.life = 1;
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
		if(spriteCounter>15)
		{
			if(spriteNum==1)
				spriteNum=2;
		else if(spriteNum==2)
			spriteNum=1;
		spriteCounter=0;
		}
		
	}
	public BufferedImage setup(String imagePath, int width, int height) {
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
		int screenX=worldX-gp.player.worldX+gp.player.screenX;
		int screenY=worldY-gp.player.worldY+gp.player.screenY;
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
			
		g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null); 
}
	}
