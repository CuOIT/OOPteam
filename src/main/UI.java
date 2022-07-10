package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class UI {
	Font pixel,consola;
	GamePanel gp;
	Graphics2D g2;
	public boolean fullScreen = false; 
	private String currentDialogue;
	public int slotCol =0 ;
	public int slotRow = 0 ;
	public int subState=0;
	public UI(GamePanel gp) {
		this.gp=gp;	
		InputStream is=getClass().getResourceAsStream("/font/Autom-Bold.otf");
		InputStream is2=getClass().getResourceAsStream("/font/CONSOLA.TTF");
		try {
			pixel=Font.createFont(Font.TRUETYPE_FONT, is);
			consola=Font.createFont(Font.TRUETYPE_FONT, is2);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2) {
		this.g2=g2;
		if(gp.gameState==gp.PLAY_STATE) {
			//
			drawPlayerLife();
		}
		else if(gp.gameState==gp.TITLE_STATE)
		{
			drawTitleScreen();
		}
		else if(gp.gameState==gp.DIALOGUE_STATE)
		{	drawPlayerLife();
			drawDialogueScreen(gp.player.npcIndex);
		}
		else if(gp.gameState==gp.DIFFICULT_STATE) {
			drawDifficultState();
		}
		else if(gp.gameState==gp.CHARACTER_STATE)
		{
			//drawCharacterScreen();
			drawInventory();
		}
		else if(gp.gameState==gp.OPTION_STATE) {
			drawOptionState();
		}
		else if(gp.gameState==gp.GAME_OVER_STATE) {
			drawGameOverScreen();
		}
		else if(gp.gameState==gp.SOUND_STATE1) {
			drawSoundState1();
		}
		else if(gp.gameState==gp.SOUND_STATE2) {
			drawSoundState2();
		}
		else if(gp.gameState==gp.VICTORY_STATE) {
			drawVictoryScreen();
		}
	}
	
	public void drawDifficultState() {	
		try {
			BufferedImage titleImage=ImageIO.read(getClass().getResourceAsStream("/menu/titleScreen.png"));
			titleImage=UtilityTool.scaledImage(titleImage, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);
			g2.drawImage(titleImage,0,0,null);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedImage[][] difButton=new BufferedImage[3][3];
		BufferedImage difImage=null;
				try {
					difImage=ImageIO.read(getClass().getResourceAsStream("/menu/difficult_level.png"));
				}catch(IOException e) {
					e.printStackTrace();
				}
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				difButton[i][j]=difImage.getSubimage(i*140, j*56, 140, 56);
				difButton[i][j]=UtilityTool.scaledImage(difButton[i][j],210,84);
			//	g2.drawImage(difButton[i][j],gp.TILE_SIZE*i*5,gp.TILE_SIZE*j*5,null);
				}
		g2.drawImage(difButton[0][i],getXForCenteredImage(difButton[0][0]),gp.TILE_SIZE*i*2+7*gp.TILE_SIZE,null);
		}
			
		switch(subState) {
		case 0: 
			g2.drawImage(difButton[1][0],getXForCenteredImage(difButton[0][0]),gp.TILE_SIZE*0*2+7*gp.TILE_SIZE,null);
			break;
		case 1:
			g2.drawImage(difButton[1][1],getXForCenteredImage(difButton[0][0]),gp.TILE_SIZE*1*2+7*gp.TILE_SIZE,null);
			break;
		case 2:
			g2.drawImage(difButton[1][2],getXForCenteredImage(difButton[0][0]),gp.TILE_SIZE*2*2+7*gp.TILE_SIZE,null);
			break;
		}
	}

	
	public void drawSoundState1() {
		try {
			BufferedImage titleImage=ImageIO.read(getClass().getResourceAsStream("/menu/titleScreen.png"));
			titleImage=UtilityTool.scaledImage(titleImage, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);
			g2.drawImage(titleImage,0,0,null);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedImage[][] menuButton=new BufferedImage[3][3];
		BufferedImage menuImage=null;
				try {
					menuImage=ImageIO.read(getClass().getResourceAsStream("/menu/button_atlas.png"));
				}catch(IOException e) {
					e.printStackTrace();
				}
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				menuButton[i][j]=menuImage.getSubimage(i*140, j*56, 140, 56);
				menuButton[i][j]=UtilityTool.scaledImage(menuButton[i][j],210,84);
			//	g2.drawImage(menuButton[i][j],gp.TILE_SIZE*i*5,gp.TILE_SIZE*j*5,null);
				}
		g2.drawImage(menuButton[0][i],getXForCenteredImage(menuButton[0][0]),gp.TILE_SIZE*i*2+7*gp.TILE_SIZE,null);
		}
		BufferedImage[][] sound=new BufferedImage[2][3];
		BufferedImage soundButtons=null;
				try {
					soundButtons=ImageIO.read(getClass().getResourceAsStream("/menu/soundButton.png"));
				}catch(IOException e) {
					e.printStackTrace();
				}
		for(int i=0;i<3;i++) {
			for(int j=0;j<2;j++) {
				sound[j][i]=soundButtons.getSubimage(i*42, j*42, 42, 42);
				sound[j][i]=UtilityTool.scaledImage(sound[j][i],84,84);
			//	g2.drawImage(menuButton[i][j],gp.TILE_SIZE*i*5,gp.TILE_SIZE*j*5,null);
				}
			g2.drawImage(sound[1][i],gp.TILE_SIZE*10+84*i,gp.TILE_SIZE*9,null);
		}
		switch(subState) {
		case 0: 
			g2.drawImage(sound[0][0],gp.TILE_SIZE*10,gp.TILE_SIZE*9,null);
			break;
		case 1:
			g2.drawImage(sound[0][1],gp.TILE_SIZE*10+84,gp.TILE_SIZE*9,null);
			break;
		case 2:
			g2.drawImage(sound[0][2],gp.TILE_SIZE*10+84*2,gp.TILE_SIZE*9,null);
			break;
		}
	}
	public void drawSoundState2() {
		BufferedImage menuBackground=null;
		try {
			menuBackground=ImageIO.read(getClass().getResourceAsStream("/menu/menu_background.png"));
			menuBackground=UtilityTool.scaledImage(menuBackground, gp.TILE_SIZE*10, gp.TILE_SIZE*12);
		}catch(IOException e) {
			e.printStackTrace();
		}
		int x=getXForCenteredImage(menuBackground);
		int y=gp.TILE_SIZE;
		g2.drawImage(menuBackground,x,y,null);
		BufferedImage[][] menuButton=new BufferedImage[3][3];
		BufferedImage menuImage=null;
				try {
					menuImage=ImageIO.read(getClass().getResourceAsStream("/menu/button_atlas.png"));
				}catch(IOException e) {
					e.printStackTrace();
				}
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				menuButton[i][j]=menuImage.getSubimage(i*140, j*56, 140, 56);
				menuButton[i][j]=UtilityTool.scaledImage(menuButton[i][j],210,84);
			//	g2.drawImage(menuButton[i][j],gp.TILE_SIZE*i*5,gp.TILE_SIZE*j*5,null);
				}
		g2.drawImage(menuButton[0][i],getXForCenteredImage(menuButton[0][0]),gp.TILE_SIZE*i*2+5*gp.TILE_SIZE,null);
		}

		BufferedImage[][] sound=new BufferedImage[2][3];
		BufferedImage soundButtons=null;
				try {
					soundButtons=ImageIO.read(getClass().getResourceAsStream("/menu/soundButton.png"));
				}catch(IOException e) {
					e.printStackTrace();
				}
		for(int i=0;i<3;i++) {
			for(int j=0;j<2;j++) {
				sound[j][i]=soundButtons.getSubimage(i*42, j*42, 42, 42);
				sound[j][i]=UtilityTool.scaledImage(sound[j][i],84,84);
			//	g2.drawImage(menuButton[i][j],gp.TILE_SIZE*i*5,gp.TILE_SIZE*j*5,null);
				}
			g2.drawImage(sound[1][i],getXForCenteredImage(menuButton[0][0])+84*i,gp.TILE_SIZE*7,null);
		}
		switch(subState) {
		case 0: 
			g2.drawImage(sound[0][0],getXForCenteredImage(menuButton[0][0]),gp.TILE_SIZE*7,null);
			break;
		case 1:
			g2.drawImage(sound[0][1],getXForCenteredImage(menuButton[0][0])+84,gp.TILE_SIZE*7,null);
			break;
		case 2:
			g2.drawImage(sound[0][2],getXForCenteredImage(menuButton[0][0])+84*2,gp.TILE_SIZE*7,null);
			break;
		}
	}
	public void drawOptionState() {
		BufferedImage menuBackground=null;
		try {
			menuBackground=ImageIO.read(getClass().getResourceAsStream("/menu/menu_background.png"));
			menuBackground=UtilityTool.scaledImage(menuBackground, gp.TILE_SIZE*10, gp.TILE_SIZE*12);
		}catch(IOException e) {
			e.printStackTrace();
		}
		int x=getXForCenteredImage(menuBackground);
		int y=gp.TILE_SIZE;
		g2.drawImage(menuBackground,x,y,null);
		BufferedImage[][] menuButton=new BufferedImage[3][3];
		BufferedImage menuImage=null;
				try {
					menuImage=ImageIO.read(getClass().getResourceAsStream("/menu/button_atlas.png"));
				}catch(IOException e) {
					e.printStackTrace();
				}
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				menuButton[i][j]=menuImage.getSubimage(i*140, j*56, 140, 56);
				menuButton[i][j]=UtilityTool.scaledImage(menuButton[i][j],210,84);
			//	g2.drawImage(menuButton[i][j],gp.TILE_SIZE*i*5,gp.TILE_SIZE*j*5,null);
				}
		g2.drawImage(menuButton[0][i],getXForCenteredImage(menuButton[0][0]),gp.TILE_SIZE*i*2+5*gp.TILE_SIZE,null);
		}
			
		switch(subState) {
		case 0: 
			g2.drawImage(menuButton[1][0],getXForCenteredImage(menuButton[0][0]),gp.TILE_SIZE*0*2+5*gp.TILE_SIZE,null);
			break;
		case 1:
			g2.drawImage(menuButton[1][1],getXForCenteredImage(menuButton[0][0]),gp.TILE_SIZE*1*2+5*gp.TILE_SIZE,null);
			break;
		case 2:
			g2.drawImage(menuButton[1][2],getXForCenteredImage(menuButton[0][0]),gp.TILE_SIZE*2*2+5*gp.TILE_SIZE,null);
			break;
		}
		
	
	}
	public void drawPlayerLife() {
		int x = gp.TILE_SIZE ;
		int y = gp.TILE_SIZE * 3 /2;
		int width = gp.TILE_SIZE*4;
		int height = gp.TILE_SIZE / 5 *2 ;


		double oneScale = (double)gp.TILE_SIZE/gp.player.maxHp;
		double hpBarValue = oneScale* gp.player.hp * 4;
		
//		g2.setColor(Color.black);
//		g2.fillRoundRect(x - 2, y - 18,width+6  ,height +8, 20, 20);
		//Thanh maxHP
		g2.setColor(new Color(35, 35, 35));
		g2.fillRoundRect(x - 3, y - 18,width+7 ,height +12, 30, 30);

		//Thanh hp
		g2.setColor(new Color(105,25,25));
		g2.fillRoundRect(x , y - 15, 4*gp.TILE_SIZE , 23, 20, 20);
		g2.setColor(new Color(255,0,30));
		g2.fillRoundRect(x , y - 15, (int)hpBarValue , 23, 20, 20);

		try {
			BufferedImage heartImage=ImageIO.read(getClass().getResourceAsStream("/objects/hp_full.png"));
			heartImage=UtilityTool.scaledImage(heartImage, gp.TILE_SIZE, gp.TILE_SIZE);
			
			
			for (int i=0;i<gp.player.life;i++) {
				g2.drawImage(heartImage , x + 20 + gp.TILE_SIZE * i , y +10, null);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			BufferedImage headPlayerImage=ImageIO.read(getClass().getResourceAsStream("/player/playerHead.png"));
			headPlayerImage=UtilityTool.scaledImage(headPlayerImage, gp.TILE_SIZE*3/2, gp.TILE_SIZE*3/2);
			
			g2.drawImage(headPlayerImage,gp.TILE_SIZE/8,gp.TILE_SIZE*7/10,null);
			}
		 catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//DEBUG
		
		int x1=gp.TILE_SIZE*2;
		int y1=gp.TILE_SIZE*4;
		int x2=gp.TILE_SIZE*2;
		int y2=gp.TILE_SIZE*5;
		int x3=gp.TILE_SIZE*2;
		int y3=gp.TILE_SIZE*6;
		String wX=String.valueOf(gp.player.worldX/gp.TILE_SIZE);
		String wY=String.valueOf(gp.player.worldY/gp.TILE_SIZE);
		String text=String.valueOf(gp.player.currentMission);
		g2.drawString(wX,x1,y1);
		g2.drawString(wY, x2, y2);
		g2.drawString(text,x3,y3);
	}
	 
	public void drawDialogueScreen(int i) {
			int x = gp.TILE_SIZE*2;
			int y = gp.TILE_SIZE/2;
			int width= gp.SCREEN_WIDTH - (gp.TILE_SIZE*4);
			int height= gp.TILE_SIZE*4;
			drawSubWindow(x,y,width,height);
			
			g2.setFont(consola.deriveFont(32F));
			x+=gp.TILE_SIZE;
			y+=gp.TILE_SIZE;
			
			if(gp.player.currentMission==0) {
			currentDialogue=gp.player.dialogue[0][0];
			g2.drawString(currentDialogue,x,y);
			}
			else {
			currentDialogue=gp.npc[gp.currentMap][i].dialogue[gp.player.currentMission][gp.npc[gp.currentMap][i].numberDialogue];
			if(currentDialogue!=null) {
				g2.drawString(currentDialogue, x, y);
			}
			}

	}
	public void drawTitleScreen() {
		try {
			BufferedImage titleImage=ImageIO.read(getClass().getResourceAsStream("/menu/titleScreen.png"));
			titleImage=UtilityTool.scaledImage(titleImage, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);
			g2.drawImage(titleImage,0,0,null);
		} catch (IOException e1) {
		}
		g2.setColor(Color.white);
		g2.setFont(pixel.deriveFont(80F));
		String text="LOST TO SKULL ISLAND";
		int x=getXForCenteredText(text);
		int y=3*gp.TILE_SIZE;
		g2.drawString(text,x,y);
		BufferedImage[][] menuButton=new BufferedImage[3][3];
		BufferedImage menuImage=null;
				try {
					menuImage=ImageIO.read(getClass().getResourceAsStream("/menu/button_atlas.png"));
				}catch(IOException e) {
					e.printStackTrace();
				}
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				menuButton[i][j]=menuImage.getSubimage(i*140, j*56, 140, 56);
				menuButton[i][j]=UtilityTool.scaledImage(menuButton[i][j],210,84);
			//	g2.drawImage(menuButton[i][j],gp.TILE_SIZE*i*5,gp.TILE_SIZE*j*5,null);
				}
		g2.drawImage(menuButton[0][i],getXForCenteredImage(menuButton[0][0]),gp.TILE_SIZE*i*2+7*gp.TILE_SIZE,null);
		}
			
		switch(subState) {
		case 0: 
			g2.drawImage(menuButton[1][0],getXForCenteredImage(menuButton[0][0]),gp.TILE_SIZE*0*2+7*gp.TILE_SIZE,null);
			break;
		case 1:
			g2.drawImage(menuButton[1][1],getXForCenteredImage(menuButton[0][0]),gp.TILE_SIZE*1*2+7*gp.TILE_SIZE,null);
			break;
		case 2:
			g2.drawImage(menuButton[1][2],getXForCenteredImage(menuButton[0][0]),gp.TILE_SIZE*2*2+7*gp.TILE_SIZE,null);
			break;
		}
	}
	
	public void drawGameOverScreen(){
	g2.setColor(Color.black);
	g2.fillRect(0,-gp.SCREEN_HEIGHT/2+gp.frameCounter*(gp.SCREEN_HEIGHT/240), gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT/2);
	g2.fillRect(0,gp.SCREEN_HEIGHT-gp.frameCounter*(gp.SCREEN_HEIGHT/240), gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT/2);

	if(gp.frameCounter==120) {
	g2.setColor(Color.white);
	g2.setFont(pixel.deriveFont(80F));
	String text="GAME OVER";
	int x=getXForCenteredText(text);
	int y=gp.SCREEN_HEIGHT/2-3*gp.TILE_SIZE;
	g2.drawString(text,x,y);
	g2.setFont(pixel.deriveFont(32F));
	String text2="Press ENTER to exit";
	int x2=getXForCenteredText(text2);
	int y2=gp.SCREEN_HEIGHT-2*gp.TILE_SIZE;
	g2.drawString(text2,x2,y2);
	}
	}
	public void drawVictoryScreen(){
	g2.setColor(Color.black);
	g2.fillRect(0,-gp.SCREEN_HEIGHT/2+gp.frameCounter*(gp.SCREEN_HEIGHT/240), gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT/2);
	g2.fillRect(0,gp.SCREEN_HEIGHT-gp.frameCounter*(gp.SCREEN_HEIGHT/240), gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT/2);

	if(gp.frameCounter==120) {
	g2.setColor(Color.white);
	g2.setFont(pixel.deriveFont(80F));
	String text="VICTORY";
	int x=getXForCenteredText(text);
	int y=gp.SCREEN_HEIGHT/2-3*gp.TILE_SIZE;
	g2.drawString(text,x,y);
	g2.setFont(pixel.deriveFont(32F));
	String text2="Press ENTER to exit";
	int x2=getXForCenteredText(text2);
	int y2=gp.SCREEN_HEIGHT-2*gp.TILE_SIZE;
	g2.drawString(text2,x2,y2);
	}
	}
	public void drawInventory() {
		// FRAME
		int frameX = gp.TILE_SIZE*9 ;
		int frameY = gp.TILE_SIZE;
		int frameWidth = gp.TILE_SIZE*6;
		int frameHeight = gp.TILE_SIZE*5 ;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		// SLOT
		final int slotXstart = frameX + 20 ;
		final int slotYstart = frameY + 20 ;
		int slotX = slotXstart  ;
		int slotY = slotYstart  ;
		int slotSize = gp.TILE_SIZE + 3;
		
		//DRAW PLAYER'S ITEMS
		for(int i= 0;i< gp.player.inventory.size();i++) {
//			
//			if(gp.player.inventory.get(i) == gp.player.currentWeapon ||
//					gp.player.inventory.get(i) == gp.player.bow) {
//				g2.setColor(new Color(240,190,90));
//				g2.fillRoundRect(slotX, slotY, gp.TILE_SIZE, gp.TILE_SIZE, 10,10);
//			}
			g2.drawImage(gp.player.inventory.get(i).down1,slotX, slotY, null);
			if(gp.player.inventory.get(i).amount >1) {

				g2.setFont(consola.deriveFont(32F));
				int amountX;
				int amountY;

				String s = "" + gp.player.inventory.get(i).amount;
				amountX = forAlignToRightText(s, slotX +44) ;
				amountY = slotY + gp.TILE_SIZE ;

				//SHADOW
				g2.setColor(new Color(60,60,60));
				g2.drawString(s, amountX, amountY);
				//NUMBER
				g2.setColor(Color.white);
				g2.drawString(s, amountX, amountY);
			}
			slotX += slotSize;
					
			if(i==4 || i==9 || i==14) {
				slotX = slotXstart;
				slotY += slotSize;
			}
		}
		
		//CURSOR
		int cursorX = slotXstart + (slotSize * slotCol -2);
		int cursorY = slotXstart + (slotSize * slotRow -379);
		int cursorWidth= gp.TILE_SIZE  ;
		int cursorHeight= gp.TILE_SIZE ;
		
		//DRAW CURSOR
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
		
		// DESCRIPTION FRAME 
		int dFrameX = frameX;
		int dFrameY = frameY+ frameHeight;
		int dFrameWidth = frameWidth;
		int dFrameHeight = gp.TILE_SIZE*3;
		// cut dong nay drawsubwindow
		
		
		// DRAW DESCRIPTION TEXT
		int textX = dFrameX + 20;
		int textY = dFrameY + gp.TILE_SIZE;
		g2.setFont(consola.deriveFont(28F));
		
		int itemIndex = getItemIndexOnSlot();
		if(itemIndex < gp.player.inventory.size()) {
			//paste 
			drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
			// xuoi day
			for(String line: gp.player.inventory.get(itemIndex).description.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 32;
	}
	}
	}
	
	public int getItemIndexOnSlot() {
		int itemIndex = slotCol + (slotRow *5);
		return itemIndex;
	}

	public void drawSubWindow(int x,int y,int width,int height) {
		Color c=new Color(0,0,0,210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		c=new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10,25,25);
	}
	
	public int forAlignToRightText(String text, int tailX) {

		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
	
	public int getXForCenteredText(String text) {
		int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
		int x=gp.SCREEN_WIDTH/2-length/2;
		return x;
	}
	public int getXForCenteredImage(BufferedImage image) {
		return (int)(gp.SCREEN_WIDTH/2-image.getWidth()/2);
	}

	
}
