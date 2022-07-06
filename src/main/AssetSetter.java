package main;

import object.OBJ_Entry_Cave;
import object.OBJ_Pine_tree;
import object.OBJ_Pit;
import object.OBJ_Tent;
import object.OBJ_Tree;
import entity.NPC_guard;
import entity.NPC_headman;
import object.OBJ_Apple;
import entity.Boar_monster;
public class AssetSetter {

	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp=gp;
	}
	public void setObject() {
		int mapNum=0;
		//PineTree
		//tree
		gp.obj[mapNum][0]=new OBJ_Tree(gp);
		gp.obj[mapNum][0].worldX=17*gp.TILE_SIZE;
		gp.obj[mapNum][0].worldY=5*gp.TILE_SIZE;
		
		gp.obj[mapNum][1]=new OBJ_Tree(gp);
		gp.obj[mapNum][1].worldX=19*gp.TILE_SIZE;
		gp.obj[mapNum][1].worldY=5*gp.TILE_SIZE;
		
		gp.obj[mapNum][2]=new OBJ_Pine_tree(gp) ;
		gp.obj[mapNum][2].worldX=10*gp.TILE_SIZE;
		gp.obj[mapNum][2].worldY=5*gp.TILE_SIZE;
		
		gp.obj[mapNum][3]=new OBJ_Pine_tree(gp) ;
		gp.obj[mapNum][3].worldX=19*gp.TILE_SIZE;
		gp.obj[mapNum][3].worldY=9*gp.TILE_SIZE;
	
		gp.obj[mapNum][4]=new OBJ_Pine_tree(gp) ;
		gp.obj[mapNum][4].worldX=22*gp.TILE_SIZE;
		gp.obj[mapNum][4].worldY=13*gp.TILE_SIZE;
		
		gp.obj[mapNum][5]=new OBJ_Pine_tree(gp) ;
		gp.obj[mapNum][5].worldX=21*gp.TILE_SIZE;
		gp.obj[mapNum][5].worldY=18*gp.TILE_SIZE;
		
		gp.obj[mapNum][6]=new OBJ_Pine_tree(gp) ;
		gp.obj[mapNum][6].worldX=16*gp.TILE_SIZE;
		gp.obj[mapNum][6].worldY=23*gp.TILE_SIZE;
		
		gp.obj[mapNum][7]=new OBJ_Pine_tree(gp) ;
		gp.obj[mapNum][7].worldX=11*gp.TILE_SIZE;
		gp.obj[mapNum][7].worldY=25*gp.TILE_SIZE;
		
		gp.obj[mapNum][8]=new OBJ_Pine_tree(gp);
		gp.obj[mapNum][8].worldX=32*gp.TILE_SIZE;
		gp.obj[mapNum][8].worldY=1*gp.TILE_SIZE;
		
		gp.obj[mapNum][9]=new OBJ_Pine_tree(gp) ;
		gp.obj[mapNum][9].worldX=41*gp.TILE_SIZE;
		gp.obj[mapNum][9].worldY=4*gp.TILE_SIZE;
		
		gp.obj[mapNum][90]=new OBJ_Pine_tree(gp) ;
		gp.obj[mapNum][90].worldX=33*gp.TILE_SIZE;
		gp.obj[mapNum][90].worldY=29*gp.TILE_SIZE;
		
		gp.obj[mapNum][91]=new OBJ_Pine_tree(gp) ;
		gp.obj[mapNum][91].worldX=20*gp.TILE_SIZE;
		gp.obj[mapNum][91].worldY=29*gp.TILE_SIZE;
		
		gp.obj[mapNum][91]=new OBJ_Pine_tree(gp) ;
		gp.obj[mapNum][91].worldX=20*gp.TILE_SIZE;
		gp.obj[mapNum][91].worldY=29*gp.TILE_SIZE;
		
		gp.obj[mapNum][92]=new OBJ_Pine_tree(gp) ;
		gp.obj[mapNum][92].worldX=28*gp.TILE_SIZE;
		gp.obj[mapNum][92].worldY=31*gp.TILE_SIZE;
		
		
		gp.obj[mapNum][93]=new OBJ_Pine_tree(gp) ;
		gp.obj[mapNum][93].worldX=45*gp.TILE_SIZE;
		gp.obj[mapNum][93].worldY=8*gp.TILE_SIZE;
		
		gp.obj[mapNum][94]=new OBJ_Pine_tree(gp) ;
		gp.obj[mapNum][94].worldX=36*gp.TILE_SIZE;
		gp.obj[mapNum][94].worldY=36*gp.TILE_SIZE;
		
		gp.obj[mapNum][95]=new OBJ_Pine_tree(gp) ;
		gp.obj[mapNum][95].worldX=34*gp.TILE_SIZE;
		gp.obj[mapNum][95].worldY=41*gp.TILE_SIZE;
		
		gp.obj[mapNum][96]=new OBJ_Pine_tree(gp) ;
		gp.obj[mapNum][96].worldX=14*gp.TILE_SIZE;
		gp.obj[mapNum][96].worldY=42*gp.TILE_SIZE;
		
		
	
		gp.obj[mapNum][10]=new OBJ_Tree(gp);
		gp.obj[mapNum][10].worldX=29*gp.TILE_SIZE;
		gp.obj[mapNum][10].worldY=7*gp.TILE_SIZE;
		
		gp.obj[mapNum][11]=new OBJ_Tree(gp);
		gp.obj[mapNum][11].worldX=31*gp.TILE_SIZE;
		gp.obj[mapNum][11].worldY=7*gp.TILE_SIZE;
		
		gp.obj[mapNum][12]=new OBJ_Tree(gp);
		gp.obj[mapNum][12].worldX=21*gp.TILE_SIZE;
		gp.obj[mapNum][12].worldY=7*gp.TILE_SIZE;
		
		gp.obj[mapNum][13]=new OBJ_Tree(gp);
		gp.obj[mapNum][13].worldX=23*gp.TILE_SIZE;
		gp.obj[mapNum][13].worldY=7*gp.TILE_SIZE;
		
		gp.obj[mapNum][14]=new OBJ_Tree(gp);
		gp.obj[mapNum][14].worldX=25*gp.TILE_SIZE;
		gp.obj[mapNum][14].worldY=7*gp.TILE_SIZE;
		
		gp.obj[mapNum][15]=new OBJ_Tree(gp);
		gp.obj[mapNum][15].worldX=23*gp.TILE_SIZE;
		gp.obj[mapNum][15].worldY=9*gp.TILE_SIZE;
		
		gp.obj[mapNum][16]=new OBJ_Tree(gp);
		gp.obj[mapNum][16].worldX=25*gp.TILE_SIZE;
		gp.obj[mapNum][16].worldY=9*gp.TILE_SIZE;
		
		gp.obj[mapNum][17]=new OBJ_Tree(gp);
		gp.obj[mapNum][17].worldX=21*gp.TILE_SIZE;
		gp.obj[mapNum][17].worldY=5*gp.TILE_SIZE;
		
		gp.obj[mapNum][18]=new OBJ_Tree(gp);
		gp.obj[mapNum][18].worldX=13*gp.TILE_SIZE;
		gp.obj[mapNum][18].worldY=4*gp.TILE_SIZE;
		
		gp.obj[mapNum][19]=new OBJ_Tree(gp);
		gp.obj[mapNum][19].worldX=15*gp.TILE_SIZE;
		gp.obj[mapNum][19].worldY=4*gp.TILE_SIZE;
		
		gp.obj[mapNum][20]=new OBJ_Tree(gp);
		gp.obj[mapNum][20].worldX=17*gp.TILE_SIZE;
		gp.obj[mapNum][20].worldY=3*gp.TILE_SIZE;
		
		gp.obj[mapNum][21]=new OBJ_Tree(gp);
		gp.obj[mapNum][21].worldX=19*gp.TILE_SIZE;
		gp.obj[mapNum][21].worldY=3*gp.TILE_SIZE;
		
		gp.obj[mapNum][22]=new OBJ_Tree(gp);
		gp.obj[mapNum][22].worldX=25*gp.TILE_SIZE;
		gp.obj[mapNum][22].worldY=11*gp.TILE_SIZE;
		
		gp.obj[mapNum][23]=new OBJ_Tree(gp);
		gp.obj[mapNum][23].worldX=25*gp.TILE_SIZE;
		gp.obj[mapNum][23].worldY=13*gp.TILE_SIZE;
		
		gp.obj[mapNum][24]=new OBJ_Tree(gp);
		gp.obj[mapNum][24].worldX=25*gp.TILE_SIZE;
		gp.obj[mapNum][24].worldY=15*gp.TILE_SIZE;
		
		gp.obj[mapNum][25]=new OBJ_Tree(gp);
		gp.obj[mapNum][25].worldX=25*gp.TILE_SIZE;
		gp.obj[mapNum][25].worldY=17*gp.TILE_SIZE;
		
		gp.obj[mapNum][26]=new OBJ_Tree(gp);
		gp.obj[mapNum][26].worldX=25*gp.TILE_SIZE;
		gp.obj[mapNum][26].worldY=20*gp.TILE_SIZE;
		
		gp.obj[mapNum][27]=new OBJ_Tree(gp);
		gp.obj[mapNum][27].worldX=23*gp.TILE_SIZE;
		gp.obj[mapNum][27].worldY=21*gp.TILE_SIZE;
		
		gp.obj[mapNum][28]=new OBJ_Tree(gp);
		gp.obj[mapNum][28].worldX=20*gp.TILE_SIZE;
		gp.obj[mapNum][28].worldY=23*gp.TILE_SIZE;
		
		gp.obj[mapNum][29]=new OBJ_Tree(gp);
		gp.obj[mapNum][29].worldX=21*gp.TILE_SIZE;
		gp.obj[mapNum][29].worldY=22*gp.TILE_SIZE;
		
		gp.obj[mapNum][30]=new OBJ_Tree(gp);
		gp.obj[mapNum][30].worldX=18*gp.TILE_SIZE;
		gp.obj[mapNum][30].worldY=24*gp.TILE_SIZE;
		
		gp.obj[mapNum][31]=new OBJ_Tree(gp);
		gp.obj[mapNum][31].worldX=16*gp.TILE_SIZE;
		gp.obj[mapNum][31].worldY=25*gp.TILE_SIZE;
		
		gp.obj[mapNum][32]=new OBJ_Tree(gp);
		gp.obj[mapNum][32].worldX=16*gp.TILE_SIZE;
		gp.obj[mapNum][32].worldY=27*gp.TILE_SIZE;
		
		
		for (int i=1; i<=7; i++) {
		gp.obj[mapNum][32+i]=new OBJ_Tree(gp);
		gp.obj[mapNum][32+i].worldX=(23+2*i)*gp.TILE_SIZE;
		gp.obj[mapNum][32+i].worldY=3*gp.TILE_SIZE;
		}
		
		for (int i=1; i<=5; i++) {
				gp.obj[mapNum][38+i]=new OBJ_Tree(gp);
				gp.obj[mapNum][38+i].worldX=29*gp.TILE_SIZE;
				gp.obj[mapNum][38+i].worldY=(7+2*i)*gp.TILE_SIZE;
		}
		
		for (int i=1; i<=3; i++) {
			gp.obj[mapNum][43+i]=new OBJ_Tree(gp);
			gp.obj[mapNum][43+i].worldX=31*gp.TILE_SIZE;
			gp.obj[mapNum][43+i].worldY=(7+2*i)*gp.TILE_SIZE;
	}
		for (int i=1; i<=4; i++) {
			gp.obj[mapNum][46+i]=new OBJ_Tree(gp);
			gp.obj[mapNum][46+i].worldX=39*gp.TILE_SIZE;
			gp.obj[mapNum][46+i].worldY=(3+2*i)*gp.TILE_SIZE;
	}
		
		for (int i=1; i<=2; i++) {
			gp.obj[mapNum][50+i]=new OBJ_Tree(gp);
			gp.obj[mapNum][50+i].worldX=41*gp.TILE_SIZE;
			gp.obj[mapNum][50+i].worldY=(4+2*i)*gp.TILE_SIZE;
	}
		for (int i=1; i<=4; i++) {
			gp.obj[mapNum][52+i]=new OBJ_Tree(gp);
			gp.obj[mapNum][52+i].worldX=43*gp.TILE_SIZE;
			gp.obj[mapNum][52+i].worldY=(4+2*i)*gp.TILE_SIZE;
	}
		for (int i=1; i<=3; i++) {
			gp.obj[mapNum][56+i]=new OBJ_Tree(gp);
			gp.obj[mapNum][56+i].worldX=45*gp.TILE_SIZE;
			gp.obj[mapNum][56+i].worldY=(5+2*i)*gp.TILE_SIZE;
	}
		for (int i=1; i<=3; i++) {
			gp.obj[mapNum][56+i]=new OBJ_Tree(gp);
			gp.obj[mapNum][56+i].worldX=37*gp.TILE_SIZE;
			gp.obj[mapNum][56+i].worldY=(8+2*i)*gp.TILE_SIZE;
	}
		for (int i=1; i<=3; i++) {
			gp.obj[mapNum][58+i]=new OBJ_Tree(gp);
			gp.obj[mapNum][58+i].worldX=(20+2*i)*gp.TILE_SIZE;
			gp.obj[mapNum][58+i].worldY=28*gp.TILE_SIZE;
	}
		for (int i=1; i<=2; i++) {
			gp.obj[mapNum][61+i]=new OBJ_Tree(gp);
			gp.obj[mapNum][61+i].worldX=(25+2*i)*gp.TILE_SIZE;
			gp.obj[mapNum][61+i].worldY=27*gp.TILE_SIZE;
	}
		for (int i=1; i<=3; i++) {
			gp.obj[mapNum][63+i]=new OBJ_Tree(gp);
			gp.obj[mapNum][63+i].worldX=(33+2*i)*gp.TILE_SIZE;
			gp.obj[mapNum][63+i].worldY=26*gp.TILE_SIZE;
}
		for (int i=1; i<=3; i++) {
			gp.obj[mapNum][66+i]=new OBJ_Tree(gp);
			gp.obj[mapNum][66+i].worldX=(33+2*i)*gp.TILE_SIZE;
			gp.obj[mapNum][66+i].worldY=24*gp.TILE_SIZE;
	}
		for (int i=1; i<=3; i++) {
			gp.obj[mapNum][69+i]=new OBJ_Tree(gp);
			gp.obj[mapNum][69+i].worldX=(12+2*i)*gp.TILE_SIZE;
			gp.obj[mapNum][69+i].worldY=36*gp.TILE_SIZE;
	}
		gp.obj[mapNum][72]=new OBJ_Tree(gp);
		gp.obj[mapNum][72].worldX=13*gp.TILE_SIZE;
		gp.obj[mapNum][72].worldY=38*gp.TILE_SIZE;
		for (int i=1; i<=4; i++) {
			gp.obj[mapNum][73+i]=new OBJ_Tree(gp);
			gp.obj[mapNum][73+i].worldX=(22+2*i)*gp.TILE_SIZE;
			gp.obj[mapNum][73+i].worldY=38*gp.TILE_SIZE;
		}
		for (int i=1; i<=9; i++) {
			gp.obj[mapNum][77+i]=new OBJ_Tree(gp);
			gp.obj[mapNum][77+i].worldX=(15+2*i)*gp.TILE_SIZE;
			gp.obj[mapNum][77+i].worldY=43*gp.TILE_SIZE;
		}
		gp.obj[mapNum][86]=new OBJ_Tree(gp);
		gp.obj[mapNum][86].worldX=26*gp.TILE_SIZE;
		gp.obj[mapNum][86].worldY=40*gp.TILE_SIZE;
		
		gp.obj[mapNum][87]=new OBJ_Tree(gp);
		gp.obj[mapNum][87].worldX=34*gp.TILE_SIZE;
		gp.obj[mapNum][87].worldY=38*gp.TILE_SIZE;
		
		gp.obj[mapNum][88]=new OBJ_Tree(gp);
		gp.obj[mapNum][88].worldX=34*gp.TILE_SIZE;
		gp.obj[mapNum][88].worldY=39*gp.TILE_SIZE;
		
		
		//Village
		gp.obj[mapNum][89]=new OBJ_Tent(gp);
		gp.obj[mapNum][89].worldX=22*gp.TILE_SIZE;
		gp.obj[mapNum][89].worldY=25*gp.TILE_SIZE;
		
		gp.obj[mapNum][90]=new OBJ_Tent(gp);
		gp.obj[mapNum][90].worldX=33*gp.TILE_SIZE;
		gp.obj[mapNum][90].worldY=21*gp.TILE_SIZE;
		
		gp.obj[mapNum][91]=new OBJ_Tent(gp);
		gp.obj[mapNum][91].worldX=41*gp.TILE_SIZE;
		gp.obj[mapNum][91].worldY=18*gp.TILE_SIZE;
		
		
		//Apple
		gp.obj[mapNum][97]=new OBJ_Apple(gp);
		gp.obj[mapNum][97].worldX=34*gp.TILE_SIZE;
		gp.obj[mapNum][97].worldY=2*gp.TILE_SIZE;
		
		gp.obj[mapNum][98]=new OBJ_Apple(gp);
		gp.obj[mapNum][98].worldX=40*gp.TILE_SIZE;
		gp.obj[mapNum][98].worldY=4*gp.TILE_SIZE;
		
		gp.obj[mapNum][99]=new OBJ_Apple(gp);
		gp.obj[mapNum][99].worldX=31*gp.TILE_SIZE;
		gp.obj[mapNum][99].worldY=3*gp.TILE_SIZE;
		
		gp.obj[mapNum][100]=new OBJ_Apple(gp);
		gp.obj[mapNum][100].worldX=22*gp.TILE_SIZE;
		gp.obj[mapNum][100].worldY=9*gp.TILE_SIZE;
		
		
		
		
		
		
		
		
		
		//Cave
		gp.obj[mapNum][101]=new OBJ_Entry_Cave(gp);
		gp.obj[mapNum][101].worldX=22*gp.TILE_SIZE;
		gp.obj[mapNum][101].worldY=42*gp.TILE_SIZE;
		
		
		//tree 
		
		gp.obj[mapNum][102]=new OBJ_Pine_tree(gp);
		gp.obj[mapNum][102].worldX=45*gp.TILE_SIZE;
		gp.obj[mapNum][102].worldY=39*gp.TILE_SIZE;
		
		gp.obj[mapNum][103]=new OBJ_Pine_tree(gp);
		gp.obj[mapNum][103].worldX=43*gp.TILE_SIZE;
		gp.obj[mapNum][103].worldY=38*gp.TILE_SIZE;
		
		gp.obj[mapNum][104]=new OBJ_Tree(gp);
		gp.obj[mapNum][104].worldX=41*gp.TILE_SIZE;
		gp.obj[mapNum][104].worldY=38*gp.TILE_SIZE;
		
		
		gp.obj[mapNum][105]=new OBJ_Pine_tree(gp);
		gp.obj[mapNum][105].worldX=40*gp.TILE_SIZE;
		gp.obj[mapNum][105].worldY=45*gp.TILE_SIZE;
		
		gp.obj[mapNum][106]=new OBJ_Pine_tree(gp);
		gp.obj[mapNum][106].worldX=6*gp.TILE_SIZE;
		gp.obj[mapNum][106].worldY=35*gp.TILE_SIZE;
		
		gp.obj[mapNum][107]=new OBJ_Pine_tree(gp);
		gp.obj[mapNum][107].worldX=8*gp.TILE_SIZE;
		gp.obj[mapNum][107].worldY=34*gp.TILE_SIZE;
		
		gp.obj[mapNum][108]=new OBJ_Pine_tree(gp);
		gp.obj[mapNum][108].worldX=6*gp.TILE_SIZE;
		gp.obj[mapNum][108].worldY=37*gp.TILE_SIZE;
		
		gp.obj[mapNum][109]=new OBJ_Pine_tree(gp);
		gp.obj[mapNum][109].worldX=6*gp.TILE_SIZE;
		gp.obj[mapNum][109].worldY=37*gp.TILE_SIZE;
		
		gp.obj[mapNum][110]=new OBJ_Tree(gp);
		gp.obj[mapNum][110].worldX=3*gp.TILE_SIZE;
		gp.obj[mapNum][110].worldY=40*gp.TILE_SIZE;
		
		
		gp.obj[mapNum][111]=new OBJ_Tree(gp);
		gp.obj[mapNum][111].worldX=5*gp.TILE_SIZE;
		gp.obj[mapNum][111].worldY=40*gp.TILE_SIZE;
		
		gp.obj[mapNum][112]=new OBJ_Tree(gp);
		gp.obj[mapNum][112].worldX=7*gp.TILE_SIZE;
		gp.obj[mapNum][112].worldY=40*gp.TILE_SIZE;
		
		gp.obj[mapNum][113]=new OBJ_Tree(gp);
		gp.obj[mapNum][113].worldX=7*gp.TILE_SIZE;
		gp.obj[mapNum][113].worldY=43*gp.TILE_SIZE;
		
		gp.obj[mapNum][114]=new OBJ_Tree(gp);
		gp.obj[mapNum][114].worldX=9*gp.TILE_SIZE;
		gp.obj[mapNum][114].worldY=43*gp.TILE_SIZE;
		
		gp.obj[mapNum][114]=new OBJ_Tree(gp);
		gp.obj[mapNum][114].worldX=11*gp.TILE_SIZE;
		gp.obj[mapNum][114].worldY=43*gp.TILE_SIZE;

		
		
		

	}
	public void setMonster(){
		int mapNum=0;
		gp.monster[mapNum][0]= new Boar_monster(gp);
		gp.monster[mapNum][0].worldX=gp.TILE_SIZE*37;
		gp.monster[mapNum][0].worldY=gp.TILE_SIZE*28;
		
		gp.monster[mapNum][1]= new Boar_monster(gp);
		gp.monster[mapNum][1].worldX=gp.TILE_SIZE*31;
		gp.monster[mapNum][1].worldY=gp.TILE_SIZE*35;
		
		gp.monster[mapNum][2]= new Boar_monster(gp);
		gp.monster[mapNum][2].worldX=gp.TILE_SIZE*23;
		gp.monster[mapNum][2].worldY=gp.TILE_SIZE*36;
		
		gp.monster[mapNum][3]= new Boar_monster(gp);
		gp.monster[mapNum][3].worldX=gp.TILE_SIZE*28;
		gp.monster[mapNum][3].worldY=gp.TILE_SIZE*41;
		
		gp.monster[mapNum][4]= new Boar_monster(gp);
		gp.monster[mapNum][4].worldX=gp.TILE_SIZE*24;
		gp.monster[mapNum][4].worldY=gp.TILE_SIZE*46;
		
		gp.monster[mapNum][5]= new Boar_monster(gp);
		gp.monster[mapNum][5].worldX=gp.TILE_SIZE*42;
		gp.monster[mapNum][5].worldY=gp.TILE_SIZE*46;
		
		gp.monster[mapNum][6]= new Boar_monster(gp);
		gp.monster[mapNum][6].worldX=gp.TILE_SIZE*10;
		gp.monster[mapNum][6].worldY=gp.TILE_SIZE*44;

		
	}
	public void setNPC() {
		int mapNum=0;
		gp.npc[mapNum][0] = new NPC_guard(gp);
		gp.npc[mapNum][0].worldX=gp.TILE_SIZE*39;
		gp.npc[mapNum][0].worldY=gp.TILE_SIZE*15;
		gp.npc[mapNum][1] = new NPC_headman(gp);
		gp.npc[mapNum][1].worldX=gp.TILE_SIZE*24;
		gp.npc[mapNum][1].worldY=gp.TILE_SIZE*25;
		
		
		
	}
}
