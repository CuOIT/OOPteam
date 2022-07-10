package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
public class Projectile extends Entity {
    Entity user;
    public boolean alive;
    public Projectile(GamePanel gp){
        super(gp);
    }
    public void set( int worldX, int worldY, String direction, boolean alive,int life, Entity user){
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.user = user;
        this.alive = alive;
        this.life=life;

    }
    public void update(){
    	
        if(user == gp.player){
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            if(monsterIndex != 999){
                gp.player.damageMonster(monsterIndex, attack, knockBackPower);
                alive = false;
            }
        }
        if(user != gp.player){
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if(gp.player.takingDamage == false && contactPlayer == true){
                damagePlayer(attack);
                alive = false;
            }
        }

        switch(direction){
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
            case "upleft":
            	worldX-=speed;
            	worldY-=speed;
            break;
            case "upright":
            	worldX+=speed;
            	worldY-=speed;
            break;
            case "downleft":
            	worldX-=speed;
            	worldY+=speed;
            break;
            case "downright":
            	worldX+=speed;
            	worldY+=speed;
            break;
        }

        life --;
        if(life <= 0){
            alive = false;
        }
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
	public void damagePlayer(int attack){
		if(gp.player.takingDamage == false){
			//we can give damage
			gp.player.hp -= attack;
			gp.player.takingDamage = true;
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
	}

}

