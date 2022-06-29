package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Entry_Cave;
import object.OBJ_Key;
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
		gp.obj[mapNum][0].worldX=23*gp.tileSize;
		gp.obj[mapNum][0].worldY=7*gp.tileSize;
		
		gp.obj[mapNum][1]=new OBJ_Apple(gp);
		gp.obj[mapNum][1].worldX=23*gp.tileSize;
		gp.obj[mapNum][1].worldY=40*gp.tileSize;
		
		gp.obj[mapNum][2]=new OBJ_Apple(gp) ;
		gp.obj[mapNum][2].worldX=10*gp.tileSize;
		gp.obj[mapNum][2].worldY=11*gp.tileSize;
		
		gp.obj[mapNum][3]=new OBJ_Apple(gp) ;
		gp.obj[mapNum][3].worldX=10*gp.tileSize;
		gp.obj[mapNum][3].worldY=8*gp.tileSize;
	
		gp.obj[mapNum][4]=new OBJ_Apple(gp) ;
		gp.obj[mapNum][4].worldX=21*gp.tileSize;
		gp.obj[mapNum][4].worldY=8*gp.tileSize;
		
		gp.obj[mapNum][5]=new OBJ_Pit(gp) ;
		gp.obj[mapNum][5].worldX=40*gp.tileSize;
		gp.obj[mapNum][5].worldY=44	*gp.tileSize;
		
		gp.obj[mapNum][6]=new OBJ_Tent(gp) ;
		gp.obj[mapNum][6].worldX=30*gp.tileSize;
		gp.obj[mapNum][6].worldY=38*gp.tileSize;
		
		gp.obj[mapNum][7]=new OBJ_Entry_Cave(gp);
		gp.obj[mapNum][7].worldX=15*gp.tileSize;
		gp.obj[mapNum][7].worldY=42*gp.tileSize;
		
//		gp.obj[7]=new OBJ_Tent(gp) ;
//		gp.obj[7].worldX=36*gp.tileSize;
//		gp.obj[7].worldY=39*gp.tileSize;
//		
//		gp.obj[8]=new OBJ_Pit(gp) ;
//		gp.obj[8].worldX=36*gp.tileSize;
//		gp.obj[8].worldY=40*gp.tileSize;
//		
//		gp.obj[9]=new OBJ_Tent(gp) ;
//		gp.obj[9].worldX=41*gp.tileSize;
//		gp.obj[9].worldY=40*gp.tileSize;
//		
//		gp.obj[10]=new OBJ_Pit(gp) ;
//		gp.obj[10].worldX=41*gp.tileSize;
//		gp.obj[10].worldY=41*gp.tileSize;
//		
//		gp.obj[11]=new OBJ_Tent(gp) ;
//		gp.obj[11].worldX= 35*gp.tileSize;
//		gp.obj[11].worldY=43*gp.tileSize;
//		
//		gp.obj[12]=new OBJ_Pit(gp) ;
//		gp.obj[12].worldX=35*gp.tileSize;
//		gp.obj[12].worldY=44*gp.tileSize;
//		
//		gp.obj[13]=new OBJ_Tent(gp) ;
//		gp.obj[13].worldX=43*gp.tileSize;
//		gp.obj[13].worldY=38*gp.tileSize;
//		
//		gp.obj[14]=new OBJ_Pit(gp) ;
//		gp.obj[14].worldX=43*gp.tileSize;
//		gp.obj[14].worldY=39*gp.tileSize;
//		
//		gp.obj[15]=new OBJ_Pit(gp) ;
//		gp.obj[15].worldX=39*gp.tileSize;
//		gp.obj[15].worldY=41*gp.tileSize;

	}
	public void setMonster(){
		int mapNum=0;
		gp.monster[mapNum][0]= new Boar_monster(gp);
		gp.monster[mapNum][0].worldX=gp.tileSize*23;
		gp.monster[mapNum][0].worldY=gp.tileSize*36;

		gp.monster[mapNum][1]= new Boar_monster(gp);
		gp.monster[mapNum][1].worldX=gp.tileSize*23;
		gp.monster[mapNum][1].worldY=gp.tileSize*32;
		gp.monster[mapNum][2]= new Boar_monster(gp);
		gp.monster[mapNum][2].worldX=gp.tileSize*22;
		gp.monster[mapNum][2].worldY=gp.tileSize*37;
		gp.monster[mapNum][3]= new Boar_monster(gp);
		gp.monster[mapNum][3].worldX=gp.tileSize*23;
		gp.monster[mapNum][3].worldY=gp.tileSize*36;
		gp.monster[mapNum][4]= new Boar_monster(gp);
		gp.monster[mapNum][4].worldX=gp.tileSize*21;
		gp.monster[mapNum][4].worldY=gp.tileSize*32;
	}
	public void setNPC() {
		int mapNum=0;
		gp.npc[mapNum][0] = new NPC_guard(gp);
		gp.npc[mapNum][0].worldX=gp.tileSize*31;
		gp.npc[mapNum][0].worldY=gp.tileSize*36;
		gp.npc[mapNum][1] = new NPC_headman(gp);
		gp.npc[mapNum][1].worldX=gp.tileSize*42;
		gp.npc[mapNum][1].worldY=gp.tileSize*41;
		
		
		
	}
}
