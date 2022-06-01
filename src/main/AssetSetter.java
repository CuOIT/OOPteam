package main;

import object.OBJ_Chest;
import object.OBJ_Door;
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
		gp.obj[0]=new OBJ_Apple(gp);
		gp.obj[0].worldX=23*gp.tileSize;
		gp.obj[0].worldY=7*gp.tileSize;
		
		gp.obj[1]=new OBJ_Apple(gp);
		gp.obj[1].worldX=23*gp.tileSize;
		gp.obj[1].worldY=40*gp.tileSize;
		
		gp.obj[2]=new OBJ_Apple(gp) ;
		gp.obj[2].worldX=10*gp.tileSize;
		gp.obj[2].worldY=11*gp.tileSize;
		
		gp.obj[3]=new OBJ_Apple(gp) ;
		gp.obj[3].worldX=10*gp.tileSize;
		gp.obj[3].worldY=8*gp.tileSize;
	
		gp.obj[4]=new OBJ_Apple(gp) ;
		gp.obj[4].worldX=21*gp.tileSize;
		gp.obj[4].worldY=8*gp.tileSize;
		
		gp.obj[5]=new OBJ_Pit(gp) ;
		gp.obj[5].worldX=40*gp.tileSize;
		gp.obj[5].worldY=44	*gp.tileSize;
		
		gp.obj[6]=new OBJ_Tent(gp) ;
		gp.obj[6].worldX=39*gp.tileSize;
		gp.obj[6].worldY=42*gp.tileSize;
		
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
		gp.monster[0]= new Boar_monster(gp);
		gp.monster[0].worldX=gp.tileSize*23;
		gp.monster[0].worldY=gp.tileSize*36;

		gp.monster[1]= new Boar_monster(gp);
		gp.monster[1].worldX=gp.tileSize*23;
		gp.monster[1].worldY=gp.tileSize*37;
	}
	public void setNPC() {
		gp.npc[0] = new NPC_guard(gp);
		gp.npc[0].worldX=gp.tileSize*31;
		gp.npc[0].worldY=gp.tileSize*36;
		gp.npc[1] = new NPC_headman(gp);
		gp.npc[1].worldX=gp.tileSize*42;
		gp.npc[1].worldY=gp.tileSize*41;
		
		
		
	}
}
