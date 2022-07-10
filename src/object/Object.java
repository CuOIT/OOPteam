package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class Object extends Entity{
	public int amount = 0;
	public boolean stackable = false;
	public String description = "";
	public Object(GamePanel gp) {
		super(gp);		
	}

	@Override
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
		g2.drawImage(image,screenX,screenY,width,height,null); 		
	}


	public void setAction() {
		// TODO Auto-generated method stub
		
	}


	public void update() {		
	};

}
