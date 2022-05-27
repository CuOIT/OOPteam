 package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; 
public class KeyHandler implements KeyListener{
	public boolean upPressed,rightPressed,downPressed,leftPressed,enterPressed;
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
				//desau
			//titleState(code);
		}
		else if(gp.gameState==gp.characterState) {
					characterState(code);
		}
	}
	
	public void titleState(int code) {
		if(code==KeyEvent.VK_ENTER)
		{
			gp.gameState=gp.playState;
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
			gp.tileM.loadMap("/map/test.txt");
		} 
		
	}
	public void pauseState(int code) {
		if(code==KeyEvent.VK_P) {
			gp.gameState=gp.playState;
		}
		 
	}
	public void dialogueState(int code){
		if(code == KeyEvent.VK_ENTER){
			gp.gameState=gp.playState;
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
	}
}
