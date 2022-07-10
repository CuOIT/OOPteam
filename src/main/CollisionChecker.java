package main;
import java.awt.Rectangle;

import entity.Entity;
public class CollisionChecker {
	GamePanel gp;
	public CollisionChecker(GamePanel gp) {
		this.gp=gp;
	}
	public void checkTile(Entity entity) {
		int entityLeftWorldX=entity.worldX+entity.solidArea.x;
		int entityRightWorldX=entity.worldX+entity.solidArea.x+entity.solidArea.width;
		int entityTopWorldY=entity.worldY+entity.solidArea.y;
		int entityBottomWorldY=entity.worldY+entity.solidArea.y+entity.solidArea.height;
		
		int entityLeftCol=entityLeftWorldX/gp.TILE_SIZE;
		int entityRightCol=entityRightWorldX/gp.TILE_SIZE;
		int entityTopRow=entityTopWorldY/gp.TILE_SIZE;
		int entityBottomRow=entityBottomWorldY/gp.TILE_SIZE;
		
		int tileNum1,tileNum2;
		switch(entity.direction) {
		case "up":
			entityTopRow=(entityTopWorldY-entity.speed)/gp.TILE_SIZE;
			tileNum1=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true)
				entity.collisionOn=true;
			break;
		case "down":
			entityBottomRow=(entityBottomWorldY+entity.speed)/gp.TILE_SIZE;
			tileNum1=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
			tileNum2=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true)
				entity.collisionOn=true;
			break;
		case "left":
			entityLeftCol=(entityLeftWorldX-entity.speed)/gp.TILE_SIZE;
			tileNum1=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
			tileNum2=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true)
				entity.collisionOn=true;
			break;
		case "right":
			entityRightCol=(entityRightWorldX+entity.speed)/gp.TILE_SIZE;
			
				tileNum1=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
				tileNum2=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
				if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true)
					entity.collisionOn=true;
			
			
			break;
		}
	}
	public int checkObject(Entity entity,boolean player) {
		int index=999;
		for(int i=0;i<gp.obj[0].length;i++) {
			if(gp.obj[gp.currentMap][i]!=null ) {
				int xE=entity.worldX+8;
				int yE=entity.worldY+16;
				Rectangle recEntity=new Rectangle(xE,yE,entity.solidArea.width,entity.solidArea.height);
						
				//Get the object's solid area position
				int x=gp.obj[gp.currentMap][i].worldX+gp.obj[gp.currentMap][i].solidArea.x;
				int y=gp.obj[gp.currentMap][i].worldY+gp.obj[gp.currentMap][i].solidArea.y;
				Rectangle recCheck=new Rectangle(x,y,gp.obj[gp.currentMap][i].solidArea.width,gp.obj[gp.currentMap][i].solidArea.height);
				switch(entity.direction) {
				case "up":		recEntity.y-=entity.speed;break;
				case "down": 	recEntity.y+=entity.speed; break;
				case "left": 	recEntity.x-=entity.speed; break;
				case "right": 	recEntity.x+=entity.speed; break;	
//				case "up":		entity.solidArea.y-=entity.speed;break;
//				case "down": 	entity.solidArea.y+=entity.speed; break;
//				case "left": 	entity.solidArea.x-=entity.speed; break;
//				case "right": 	entity.solidArea.x+=entity.speed; break;
				}

				if(recEntity.intersects(recCheck)) {
					if(gp.obj[gp.currentMap][i].collision==true) {
						entity.collisionOn=true;
					}
					else if(player==true) {
						

						index=i;
						
					}
				}
//				entity.solidArea.x=entity.solidAreaDefaultX;
//				entity.solidArea.y=entity.solidAreaDefaultY;
//				gp.obj[gp.currentMap][i].solidArea.x=gp.obj[gp.currentMap][i].solidAreaDefaultX;
//				gp.obj[gp.currentMap][i].solidArea.y=gp.obj[gp.currentMap][i].solidAreaDefaultY; 
			}
					
		}
		return index;
	}

	public int checkEntity(Entity entity,Entity[][] target) {
		int index=999;
		for(int i=0;i<target[0].length;i++) {
			int xE=entity.worldX+8;
			int yE=entity.worldY+16;
			if(target[gp.currentMap][i]!=null ) {
				//Get entity's solid area position
				Rectangle recEntity=new Rectangle(xE,yE,entity.solidArea.width,entity.solidArea.height);
				//Get the object's solid area position
				int x=target[gp.currentMap][i].worldX+target[gp.currentMap][i].solidArea.x;
				int y=target[gp.currentMap][i].worldY+target[gp.currentMap][i].solidArea.y;	
				Rectangle recTarget=new Rectangle(x,y,target[gp.currentMap][i].solidArea.width,target[gp.currentMap][i].solidArea.height);
				switch(entity.direction) {
				case "up": recEntity.y-=entity.speed; break;
				case "down": recEntity.y+=entity.speed; break;
				case "left": recEntity.x-=entity.speed; break;
				case "right": recEntity.x+=entity.speed; break;
				}
				//check 2 Rectangle intersects(DANG)
				if(recEntity.intersects(recTarget)) {
					if(target[gp.currentMap][i] != entity) {
						entity.collisionOn=true;
						index=i;
					}
				}
			}
					
		}
		return index;
	}
	public boolean checkPlayer(Entity entity) {
		boolean contactPlayer = false;
				
				int x=entity.worldX+entity.solidArea.x;
				int y=entity.worldY+entity.solidArea.y;
				Rectangle recEntity=new Rectangle(x,y,entity.solidArea.width,entity.solidArea.height);
				int xP=gp.player.worldX+gp.player.solidArea.x;
				int yP=gp.player.worldY+gp.player.solidArea.y;
				Rectangle recPlayer=new Rectangle(xP,yP,gp.player.solidArea.width,gp.player.solidArea.height); 
				switch(entity.direction) {
				case "up": recEntity.y-=entity.speed; break;
				case "down": recEntity.y+=entity.speed; break;
				case "left": recEntity.x-=entity.speed; break;
				case "right": recEntity.x+=entity.speed; break;
				}
				//check 2 Rectangle intersects
				if(recEntity.intersects(recPlayer)) {
					//if(gp.player.collision==true) {}
						entity.collisionOn=true;
						contactPlayer= true;
					
				}
				return contactPlayer;
			}
}
//					
	

