 package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import object.OBJ_Sword; 
public class KeyHandler implements KeyListener{
	public boolean upPressed,rightPressed,downPressed,leftPressed,enterPressed,shotKeyPressed;
	//DEBUG
	GamePanel gp;
	boolean checkDrawTime=false;
	public 	KeyHandler(GamePanel gp) {
		this.gp=gp;
	}
	@Override 
	public void keyTyped(KeyEvent e)
	{
		
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		int code=e.getKeyCode();
		if(gp.gameState==gp.playState) {
					playState(code);
		}
		else if(gp.gameState==gp.titleState) {
					titleState(code);
		}
		else if(gp.gameState==gp.pauseState) {
					pauseState(code);
		}
		else if(gp.gameState==gp.dialogueState) {		
			dialogueState(code);
		}
		else if(gp.gameState==gp.characterState) {
					characterState(code);
		}
		else if(gp.gameState==gp.optionState) {
			optionState(code);
		}

	}
	
	public void playState(int code) {
		if(code==KeyEvent.VK_W) {
			upPressed=true;
		}
		else if(code==KeyEvent.VK_S) {
			downPressed=true;
		}
		else if(code==KeyEvent.VK_A) {
			leftPressed=true;
		}
		else if(code==KeyEvent.VK_D) {
			rightPressed=true;
		}
		if(code == KeyEvent.VK_ENTER){
			enterPressed=true;	
		}
		else if(code==KeyEvent.VK_P) {
				gp.gameState=gp.pauseState;
		}
		else if(code==KeyEvent.VK_C) {
			gp.gameState=gp.characterState;
		}
		else if(code==KeyEvent.VK_L) {
			if(gp.currentMap==0) gp.currentMap=1;
			else gp.currentMap=0;
				
		} 
		else if(code == KeyEvent.VK_F){
			shotKeyPressed=true;	
		}

		else if(code==KeyEvent.VK_ESCAPE) {
			gp.gameState=gp.optionState;
		}
		
	}
	public void optionState(int code) {
		if(code==KeyEvent.VK_ESCAPE) { 
			gp.gameState=gp.playState;
		}
		if(code==KeyEvent.VK_W) {
			gp.ui.subState=(gp.ui.subState-1)%3;
		}
		else if(code==KeyEvent.VK_S) {
			gp.ui.subState=(gp.ui.subState+1)%3;
		}
		if(code==KeyEvent.VK_ENTER) {
		enterPressed=true;
		if(gp.ui.subState==0) {
			gp.gameState=gp.playState;
		}
		else if(gp.ui.subState==1) {
			
		}
		else if(gp.ui.subState==2) {
			gp.gameState=gp.titleState;
		}
		}
		
	}
	public void titleState(int code) {
		if(code==KeyEvent.VK_ENTER)
		{
			gp.gameState=gp.dialogueState;
		}
	}
	public void pauseState(int code) {
		if(code==KeyEvent.VK_P) {
			gp.gameState=gp.playState;
		}
		 
	}
	public void dialogueState(int code){
		if(code == KeyEvent.VK_ENTER){
//			if(gp.player.currentMission!=2 && gp.player.currentMission!=3)
//			gp.player.currentMission++;
			gp.gameState=gp.playState;
		}
		else if(code == KeyEvent.VK_SPACE) {
			if(gp.npc[gp.currentMap][gp.player.npcIndex].dialogue[gp.player.currentMission][gp.npc[gp.currentMap][gp.player.npcIndex].numberDialogue]!=null)
			gp.npc[gp.currentMap][gp.player.npcIndex].numberDialogue++;
			if(gp.player.npcIndex==0 && gp.npc[gp.currentMap][gp.player.npcIndex].numberDialogue==7 && gp.player.currentMission==2)
			{
				gp.npc[gp.currentMap][gp.player.npcIndex].dropItem(new OBJ_Sword(gp), gp.npc[gp.currentMap][gp.player.npcIndex].worldX,gp.npc[gp.currentMap][gp.player.npcIndex].worldY+gp.TILE_SIZE);
			}
			else {
				//gp.gameState=gp.playState;
				System.out.println("NULL");
			}
		}
	}
	public void characterState(int code) {
		if(code==KeyEvent.VK_C) {
			gp.gameState=gp.playState;
		}
		else {
			if (code ==KeyEvent.VK_W) {
				if(gp.ui.slotRow != 0) {
					gp.ui.slotRow--;
					
				}
			}
            if (code ==KeyEvent.VK_S) {
            	if(gp.ui.slotRow !=3) {
				gp.ui.slotRow++;
            }
            }
            if (code ==KeyEvent.VK_A) {
            	if(gp.ui.slotCol !=0) {
            		gp.ui.slotCol--;
            	}
            }
            
            if (code ==KeyEvent.VK_D) {
            	if(gp.ui.slotCol != 4) {
				gp.ui.slotCol++;
			}
            }
		}
	}
	@Override
	public void keyReleased(KeyEvent e)
	{
		int code=e.getKeyCode();
		if(code==KeyEvent.VK_W) {
			upPressed=false;
		}
		else if(code==KeyEvent.VK_S) {
			downPressed=false;
		}
		else if(code==KeyEvent.VK_A) {
			leftPressed=false;
		}
		else if(code==KeyEvent.VK_D) {
			rightPressed=false;
		}else if(code==KeyEvent.VK_L) {
			gp.gameState=gp.playState;
		}
		else if(code==KeyEvent.VK_F){
			shotKeyPressed = false;
		}
	}
}
