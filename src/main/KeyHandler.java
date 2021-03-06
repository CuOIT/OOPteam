 package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import object.OBJ_Bow;
import object.OBJ_Sword; 
public class KeyHandler implements KeyListener{
	public boolean upPressed,rightPressed,downPressed,leftPressed,enterPressed,shotKeyPressed,soundPressed=false;
	//DEBUG
	GamePanel gp;
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
		if(code == KeyEvent.VK_P) {
			if(gp.ui.fullScreen==false)
			{
			gp.setFullScreen();
			gp.ui.fullScreen=true;
			}
			else{
				gp.drawToScreen();
				gp.ui.fullScreen=false;
			}
			gp.setFullScreen();
		}
		if(gp.gameState==gp.PLAY_STATE) {
					playState(code);
		}
		else if(gp.gameState==gp.TITLE_STATE) {
					titleState(code);
		}

		else if(gp.gameState==gp.DIALOGUE_STATE) {		
			dialogueState(code);
		}
		else if(gp.gameState==gp.CHARACTER_STATE) {
					characterState(code);
		}
		else if(gp.gameState==gp.OPTION_STATE) {
			optionState(code);
		}
		else if(gp.gameState==gp.DIFFICULT_STATE) {
			difficultState(code);
		}
		else if(gp.gameState==gp.GAME_OVER_STATE) {
			gameOverState(code);
		}
		else if(gp.gameState==gp.SOUND_STATE1 ) {
			soundState1(code);
		}
		else if(gp.gameState==gp.SOUND_STATE2) {
			soundState2(code);
		}
		else if(gp.gameState==gp.VICTORY_STATE) {
			victoryState(code);
		}

	}
	
	public void victoryState(int code) {
		if(code==KeyEvent.VK_ENTER) {
			gp.stopMusic();
			gp.gameState=gp.TITLE_STATE;
			gp.playMusic(0);
		}
	}
	public void gameOverState(int code) {
		if(gp.frameCounter==120 && code==KeyEvent.VK_ENTER) {
			gp.gameState=gp.TITLE_STATE;
			gp.ui.subState=-1;
		}
	}
	public void difficultState(int code) {
		if(code==KeyEvent.VK_W) {
			gp.ui.subState=(gp.ui.subState+2)%3;
		}
		else if(code==KeyEvent.VK_S) {
			gp.ui.subState=(gp.ui.subState+1)%3;
		}
		System.out.println("KH: "+gp.ui.subState);
		if(code==KeyEvent.VK_ENTER) {
			
			enterPressed=true;
		if(gp.ui.subState==0) {
			gp.aSetter.setUpLevel("EASY");
		}
		else if(gp.ui.subState==1) {
			gp.aSetter.setUpLevel("NORMAL");
		}
		else gp.aSetter.setUpLevel("HARD");
		
		gp.aSetter.setObject();
		gp.aSetter.setNPC();
		gp.aSetter.setMonster();
		gp.gameState=gp.DIALOGUE_STATE;
		
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
		else if(code==KeyEvent.VK_C) {
			gp.gameState=gp.CHARACTER_STATE;
		}
		else if(code==KeyEvent.VK_L) {
			if(gp.currentMap==0) gp.currentMap=1;
			else gp.currentMap=0;
		
		} 
		else if(code == KeyEvent.VK_F){
			shotKeyPressed=true;	
		}

		else if(code==KeyEvent.VK_ESCAPE) {
			gp.gameState=gp.OPTION_STATE;
		}
		
	}
	public void optionState(int code) {
		if(code==KeyEvent.VK_ESCAPE) { 
			gp.gameState=gp.PLAY_STATE;
		}
		if(code==KeyEvent.VK_W) {
			gp.ui.subState=(gp.ui.subState+2)%3;
		}
		else if(code==KeyEvent.VK_S) {
			gp.ui.subState=(gp.ui.subState+1)%3;
		}
		if(code==KeyEvent.VK_ENTER) {
		enterPressed=true;
		if(gp.ui.subState==0) {
			gp.gameState=gp.PLAY_STATE;
		}
		else if(gp.ui.subState==1) {
			gp.gameState=gp.SOUND_STATE2;
		}
		else if(gp.ui.subState==2) {
			gp.gameState=gp.TITLE_STATE;
		}
			
		}
		
		
	}
	public void soundState1(int code) {
		if(code==KeyEvent.VK_W) {
			gp.ui.subState=1;
			gp.gameState=gp.TITLE_STATE;
		}
		else if(code==KeyEvent.VK_S) {
			gp.ui.subState=3;
			gp.gameState=gp.TITLE_STATE;
		}
		else if(code==KeyEvent.VK_A) {
			gp.ui.subState=(gp.ui.subState+2)%3;
		}
		else if(code==KeyEvent.VK_D) {
			gp.ui.subState=(gp.ui.subState+1)%3;
		}
		if(code==KeyEvent.VK_ENTER) {
		enterPressed=true;
		if(gp.ui.subState==0) {
			gp.music.volumeUp();
		}
		else if(gp.ui.subState==1) {
			gp.music.volumeDown();
		}
		else if(gp.ui.subState==2) {
			gp.music.mute();
		}
			
		}
	}
	public void soundState2(int code) {
		if(code==KeyEvent.VK_ESCAPE) { 
			gp.gameState=gp.PLAY_STATE;
		}
		if(code==KeyEvent.VK_W) {
			gp.ui.subState=1;
			gp.gameState=gp.PLAY_STATE;
		}
		else if(code==KeyEvent.VK_S) {
			gp.ui.subState=3;
			gp.gameState=gp.PLAY_STATE;
		}
		else if(code==KeyEvent.VK_A) {
			gp.ui.subState=(gp.ui.subState+2)%3;
		}
		else if(code==KeyEvent.VK_D) {
			gp.ui.subState=(gp.ui.subState+1)%3;
		}
		if(code==KeyEvent.VK_ENTER) {
		enterPressed=true;
		if(gp.ui.subState==0) {
			gp.music.volumeUp();
		}
		else if(gp.ui.subState==1) {
			gp.music.volumeDown();
		}
		else if(gp.ui.subState==2) {
			gp.music.mute();
		}
			
		}
	}
	public void titleState(int code) {
		if(code==KeyEvent.VK_W) {
			gp.ui.subState=(gp.ui.subState+2)%3;
		}
		else if(code==KeyEvent.VK_S) {
			gp.ui.subState=(gp.ui.subState+1)%3;
		}
		if(code==KeyEvent.VK_ENTER) {
		enterPressed=true;
		if(gp.ui.subState==0) {
			gp.gameState=gp.DIFFICULT_STATE;
			
		}
		else if(gp.ui.subState==1) {
			gp.gameState=gp.SOUND_STATE1;
		}
		else if(gp.ui.subState==2) {
			gp.gameThread=null;
		}
		}
	}
	public void dialogueState(int code){
		if(code == KeyEvent.VK_ENTER){
			if(gp.player.getCurrentMission()==0) {
				gp.gameState=gp.PLAY_STATE;
				gp.player.setCurrentMission(1);
			}
		if(gp.npc[gp.currentMap][gp.player.getNPCINdex()].dialogue[gp.player.getCurrentMission()][gp.npc[gp.currentMap][gp.player.getNPCINdex()].numberDialogue]!=null) {
			
			
		gp.npc[gp.currentMap][gp.player.getNPCINdex()].numberDialogue++;
		if(gp.player.getNPCINdex()==0 && gp.npc[gp.currentMap][gp.player.getNPCINdex()].numberDialogue==1 && gp.player.getCurrentMission()==2)
		{
			gp.npc[gp.currentMap][gp.player.getNPCINdex()].dropItem(new OBJ_Sword(gp));
		}
		if(gp.player.getNPCINdex()==1 && gp.npc[gp.currentMap][gp.player.getNPCINdex()].numberDialogue==1 && gp.player.getCurrentMission()==3)
		{
			gp.npc[gp.currentMap][gp.player.getNPCINdex()].dropItem(new OBJ_Bow(gp));
		}
	}else {
			gp.gameState=gp.PLAY_STATE;
		}
		}
		else if(code == KeyEvent.VK_SPACE) {
			gp.gameState=gp.PLAY_STATE;
		}
	}
	public void characterState(int code) {
		if(code==KeyEvent.VK_C) {
			gp.gameState=gp.PLAY_STATE;
		}
		else {
//			if (code == KeyEvent.VK_ENTER) {
//				//gp.player.selectItem();
//				//gp.gameState=gp.PLAY_STATE;
//				System.out.println("Done");
//			}//them code den day dong 174
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
		}
		else if(code==KeyEvent.VK_F){
			shotKeyPressed = false;
		}
	}
}
