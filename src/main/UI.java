package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Container;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import entity.Entity;
import object.OBJ_Heart;

public class UI {
	Font arial_40,arial_80B;
	GamePanel gp;
	Graphics2D g2;
	public boolean fullScreen = false; 
	Menu menu=new Menu(gp);
	//ArrayList<String> message = new ArrayList<>();
	//ArrayList<Integer> messageCounter = new ArrayList<>();
	//private Object drawMessage;
	private String currentDialogue;
	private int numberDialogue=1;
	public int slotCol =0 ;
	public int slotRow = 0 ;
	public int subState=0;
	public UI(GamePanel gp) {
		this.gp=gp;	
	}

	public void draw(Graphics2D g2) {
		this.g2=g2;
		g2.setFont(arial_40);
		g2.setColor(Color.white);
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
		else if(gp.gameState==gp.CHARACTER_STATE)
		{
			//drawCharacterScreen();
			drawInventory();
		}
		else if(gp.gameState==gp.OPTION_STATE) {
			drawOptionState();
		}
	}
	
	public void drawOptionState() {
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		int frameX=gp.TILE_SIZE*11;
		int frameY=gp.TILE_SIZE;
		int frameWidth=gp.TILE_SIZE*8;
		int frameHeight=gp.TILE_SIZE*12;
	//	drawSubWindow(frameX,frameY,frameWidth,frameHeight);
		g2.drawImage(menu.image,frameX,frameY,gp.TILE_SIZE*10,gp.TILE_SIZE*12,null);
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
		g2.drawImage(menuButton[0][i],gp.TILE_SIZE*14,gp.TILE_SIZE*i*2+5*gp.TILE_SIZE,null);
		}
			
		switch(subState) {
		case 0: 
			g2.drawImage(menuButton[1][0],gp.TILE_SIZE*14,gp.TILE_SIZE*0*2+5*gp.TILE_SIZE,null);
			break;
		case 1:
			g2.drawImage(menuButton[1][1],gp.TILE_SIZE*14,gp.TILE_SIZE*1*2+5*gp.TILE_SIZE,null);
			break;
		case 2:
			g2.drawImage(menuButton[1][2],gp.TILE_SIZE*14,gp.TILE_SIZE*2*2+5*gp.TILE_SIZE,null);
			break;
		}
		
	
	}
	public void options_top(int frameX,int frameY) {
		int textX;
		int textY;
		//String text="Options";
		textX=getXforCenteredText("");
		textY=frameY+gp.TILE_SIZE;
		g2.setColor(Color.WHITE);
		//g2.drawString(text,textX,textY);
		
		textX=frameX+gp.TILE_SIZE;
		textY+=gp.TILE_SIZE*2;
		BufferedImage[][] menuButton=new BufferedImage[3][3];
		BufferedImage menuImage=null;
				try {
					menuImage=ImageIO.read(getClass().getResourceAsStream("/menu/button_atlas.png"));
				}catch(IOException e) {
					e.printStackTrace();
				}
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++) {
				menuButton[i][j]=menuImage.getSubimage(i*140, j*56, 140, 56);
				g2.drawImage(menuButton[i][j],gp.TILE_SIZE*i*5,gp.TILE_SIZE*j*5,null);
				}
		
//		g2.drawString("Full Screen", textX, textY);
//		textY+=gp.TILE_SIZE*2;
//		g2.drawString("Music", textX, textY);
//		textY+=gp.TILE_SIZE*2;
//		g2.drawString("Control", textX, textY);
//		textY+=gp.TILE_SIZE*2;
//		g2.drawString("End Game", textX, textY);
//		textY+=gp.TILE_SIZE*2;
//		g2.drawString("Quit ", textX, textY);
		
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
		g2.fillRoundRect(x - 1, y - 16,width  ,height +6, 20, 20);

		//Thanh hp
		g2.setColor(new Color(255,0,30));
		g2.fillRoundRect(x , y - 15, (int)hpBarValue , 23, 20, 20);

		try {
			BufferedImage heartImage=ImageIO.read(getClass().getResourceAsStream("/objects/hp_full.png"));
			heartImage=UtilityTool.scaledImage(heartImage, gp.TILE_SIZE, gp.TILE_SIZE);
			
			
			for (int i=0;i<gp.player.life;i++) {
				g2.drawImage(heartImage , x + 6 + gp.TILE_SIZE * i , y +6 , null);
			}
		} catch (IOException e1) {
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
			
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));//marumonica
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
		g2.setColor(new Color(70,120,80));
		g2.fillRect(0, 0, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);
	 	g2.setFont(g2.getFont().deriveFont(Font.BOLD,86F));
		String text= "LOST TO ISLAND";
		int x=getXforCenteredText(text);
		int y=gp.TILE_SIZE*3;
		g2.setColor(Color.white);
		g2.drawString(text,x,y);
		try {
			BufferedImage titleImage=ImageIO.read(getClass().getResourceAsStream("/menu/titleScreen.jpg"));
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
		g2.drawImage(menuButton[0][i],gp.TILE_SIZE*10,gp.TILE_SIZE*i*2+7*gp.TILE_SIZE,null);
		}
			
		switch(subState) {
		case 0: 
			g2.drawImage(menuButton[1][0],gp.TILE_SIZE*10,gp.TILE_SIZE*0*2+7*gp.TILE_SIZE,null);
			break;
		case 1:
			g2.drawImage(menuButton[1][1],gp.TILE_SIZE*10,gp.TILE_SIZE*1*2+7*gp.TILE_SIZE,null);
			break;
		case 2:
			g2.drawImage(menuButton[1][2],gp.TILE_SIZE*10,gp.TILE_SIZE*2*2+7*gp.TILE_SIZE,null);
			break;
		}
	}
	
	public void drawPauseScreen()
	{
		String text="PAUSED";
		int x=gp.SCREEN_WIDTH/2-gp.TILE_SIZE*5/2;
		int y=gp.SCREEN_HEIGHT/2-gp.TILE_SIZE*3;
		g2.drawImage(menu.image,x,y,gp.TILE_SIZE*5,gp.TILE_SIZE*6,null);
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

				g2.setFont(g2.getFont().deriveFont(32f));
				int amountX;
				int amountY;

				String s = "" + gp.player.inventory.get(i).amount;
				amountX = getXforAlignToRightText(s, slotX +44) ;
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
		g2.setFont(g2.getFont().deriveFont(28F));
		
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
	
	public int getXforAlignToRightText(String text, int tailX) {

		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
	
	public int getXforCenteredText(String text) {
		int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
		int x=gp.SCREEN_WIDTH/2-length/2;
		return x;
	}

	
}
