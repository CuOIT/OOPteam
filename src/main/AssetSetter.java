package main;

import object.OBJ_Chest;
import object.OBJ_Entry_Cave;
import object.OBJ_Pit;
import object.OBJ_Tent;
import entity.NPC_guard;
import entity.NPC_headman;
import object.OBJ_Apple;
import object.OBJ_Boots;
import entity.Boar_monster;
public class AssetSetter {

	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp=gp;
	}
	public void setObject() {
		int mapNum=0;
		gp.obj[mapNum][0]=new OBJ_Apple(gp);
		gp.obj[mapNum][0].worldX=42*gp.TILE_SIZE;
		gp.obj[mapNum][0].worldY=10*gp.TILE_SIZE;
		
		gp.obj[mapNum][1]=new OBJ_Apple(gp);
		gp.obj[mapNum][1].worldX=38*gp.TILE_SIZE;
		gp.obj[mapNum][1].worldY=5*gp.TILE_SIZE;
		
		gp.obj[mapNum][2]=new OBJ_Apple(gp) ;
		gp.obj[mapNum][2].worldX=45*gp.TILE_SIZE;
		gp.obj[mapNum][2].worldY=20*gp.TILE_SIZE;
		
		gp.obj[mapNum][3]=new OBJ_Apple(gp) ;
		gp.obj[mapNum][3].worldX=40*gp.TILE_SIZE;
		gp.obj[mapNum][3].worldY=26*gp.TILE_SIZE;
	
		gp.obj[mapNum][4]=new OBJ_Apple(gp) ;
		gp.obj[mapNum][4].worldX=33*gp.TILE_SIZE;
		gp.obj[mapNum][4].worldY=26*gp.TILE_SIZE;
		
		gp.obj[mapNum][5]=new OBJ_Pit(gp) ;
		gp.obj[mapNum][5].worldX=36*gp.TILE_SIZE;
		gp.obj[mapNum][5].worldY=42	*gp.TILE_SIZE;
		
		gp.obj[mapNum][6]=new OBJ_Tent(gp) ;
		gp.obj[mapNum][6].worldX=34*gp.TILE_SIZE;
		gp.obj[mapNum][6].worldY=40*gp.TILE_SIZE;
		
		gp.obj[mapNum][7]=new OBJ_Entry_Cave(gp);
		gp.obj[mapNum][7].worldX=15*gp.TILE_SIZE;
		gp.obj[mapNum][7].worldY=42*gp.TILE_SIZE;
		mapNum++;
		gp.obj[mapNum][0]=new OBJ_Entry_Cave(gp);
		gp.obj[mapNum][0].worldX=15*gp.TILE_SIZE;
		gp.obj[mapNum][0].worldY=29*gp.TILE_SIZE;
	}
	public void setMonster(){
		int mapNum=0;
		gp.monster[mapNum][0]= new Boar_monster(gp);
		gp.monster[mapNum][0].worldX=gp.TILE_SIZE*23;
		gp.monster[mapNum][0].worldY=gp.TILE_SIZE*36;

		gp.monster[mapNum][1]= new Boar_monster(gp);
		gp.monster[mapNum][1].worldX=gp.TILE_SIZE*23;
		gp.monster[mapNum][1].worldY=gp.TILE_SIZE*32;
		gp.monster[mapNum][2]= new Boar_monster(gp);
		gp.monster[mapNum][2].worldX=gp.TILE_SIZE*22;
		gp.monster[mapNum][2].worldY=gp.TILE_SIZE*37;
		gp.monster[mapNum][3]= new Boar_monster(gp);
		gp.monster[mapNum][3].worldX=gp.TILE_SIZE*23;
		gp.monster[mapNum][3].worldY=gp.TILE_SIZE*36;
		gp.monster[mapNum][4]= new Boar_monster(gp);
		gp.monster[mapNum][4].worldX=gp.TILE_SIZE*21;
		gp.monster[mapNum][4].worldY=gp.TILE_SIZE*32;
	}
	public void setNPC() {
		int mapNum=0;
		gp.npc[mapNum][0] = new NPC_guard(gp);
		gp.npc[mapNum][0].worldX=gp.TILE_SIZE*31;
		gp.npc[mapNum][0].worldY=gp.TILE_SIZE*36;
		gp.npc[mapNum][1] = new NPC_headman(gp);
		gp.npc[mapNum][1].worldX=gp.TILE_SIZE*36;
		gp.npc[mapNum][1].worldY=gp.TILE_SIZE*40;
		
		
		
	}
}
