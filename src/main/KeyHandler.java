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
	
	// chinh sua thu nghiem tu dong 22-60(Dang)
		if(code == KeyEvent.VK_J){
			gp.gameState=gp.playState;
	}
			if(gp.gameState == gp.playState){
			
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
			else if(code == KeyEvent.VK_P){
				gp.gameState = gp.pauseState;
			}
			else if(code == KeyEvent.VK_ENTER){
				enterPressed=true;	
			}			
			else if(code==KeyEvent.VK_L) {
				gp.tileM.loadMap("/map/test.txt");
			}	
		}
	//PAUSE STATE
	else if(gp.gameState == gp.pauseState){
		if(code == KeyEvent.VK_P){
			gp.gameState=gp.playState;
		}
	}

	//DIALOUGE STATE
	else if(gp.gameState == gp.dialogueState){
		if(code == KeyEvent.VK_ENTER){
			gp.gameState=gp.playState;
		}
	}
}
	//ket thuc chinh sua(Dang)

	//	if(code==KeyEvent.VK_W) {
	//		upPressed=true;
	//	}
	//	else if(code==KeyEvent.VK_S) {
	//		downPressed=true;
	//	}
	//	else if(code==KeyEvent.VK_A) {
	//		leftPressed=true;
	//	}
	//	else if(code==KeyEvent.VK_D) {
	//		rightPressed=true;
	//	}
	//	else if(code==KeyEvent.VK_P) {
	//		if(gp.gameState==gp.playState)
	//			gp.gameState=gp.pauseState;
	//		else gp.gameState=gp.playState;
	//	}
		
	//	if(code == KeyEvent.VK_ENTER){
	//		gp.gameState=gp.playState;	
	//	}
		//else if(code==KeyEvent.VK_L) {
		//	gp.tileM.loadMap("/map/test.txt");
		//}

	
	
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
