package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_Key;


public class UI {
	Font arial_40,arial_80B;
	GamePanel gp;
	Graphics2D g2;
	BufferedImage heart_full,heart_half,heart_plank;
	BufferedImage keyImage;
	public boolean gameFinished = false; 
	Menu menu=new Menu(gp);
	public int commandNum;
	private Object drawMessage;
	private String currentDialogue;
	private int numberDialogue=1;
	public int slotCol =0 ;
	public int slotRow = 0 ;
	public UI(GamePanel gp) {
		this.gp=gp;

//		try {
//			//FONT CHU
//		}catch(FontFormatException e) {
//			e.printStackTrace();
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
		Entity heart = new OBJ_Heart(gp);
		heart_full=heart.image;
		heart_half=heart.image2;
		heart_plank=heart.image3;	

		
	}
	public void draw(Graphics2D g2) {
		this.g2=g2;
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		if(gp.gameState==gp.playState) {
			//
			drawPlayerLife();
		}
		else if(gp.gameState==gp.titleState)
		{
			drawTitleScreen();
		}
		else if(gp.gameState==gp.pauseState)
		{	drawPlayerLife();
			drawPauseScreen();
		}
		else if(gp.gameState==gp.dialogueState)
		{	drawPlayerLife();
			drawDialogueScreen();
		}
		else if(gp.gameState==gp.characterState)
		{
			drawCharacterScreen();
			drawInventory();
		}
	}
	
	public void drawPlayerLife() {
		int x=gp.tileSize/2;
		int y=gp.tileSize/2;
		int temp=0;
		//temp luu so tym
		while(temp<gp.player.maxLife/2) {
			g2.drawImage(heart_plank,x,y,null);
			temp++;
			x+=gp.tileSize;
		}
		for(int i=1;i<=gp.player.life/2;i++)
		{
			g2.drawImage(heart_full,x-(6-i)*gp.tileSize,y,null);
		}
		if(gp.player.life%2!=0) g2.drawImage(heart_half,x-(5-(gp.player.life/2))*gp.tileSize,y,null);
		int x1=gp.tileSize*2;
		int y1=gp.tileSize*4;
		int x2=gp.tileSize*2;
		int y2=gp.tileSize*5;
		String wX=String.valueOf(gp.player.worldX/gp.tileSize);
		String wY=String.valueOf(gp.player.currentMission);
		g2.drawString(wX,x1,y1);
		g2.drawString(wY, x2, y2);
	}
	 
	public void drawDialogueScreen() {
			int x = gp.tileSize*2;
			int y = gp.tileSize/2;
			int width= gp.screenWidth - (gp.tileSize*4);
			int height= gp.tileSize*4;
			drawSubWindow(x,y,width,height);
			
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));//marumonica
			x+=gp.tileSize;
			y+=gp.tileSize;

			
			switch(gp.player.currentMission) {
			case 0:
				currentDialogue=gp.player.dialogue[0];
				g2.drawString(currentDialogue,x,y);
				break;
			case 1:
				currentDialogue=gp.npc[0].dialogue[gp.npc[0].numberDialogue];
				if(currentDialogue!=null) {
					g2.drawString(currentDialogue,x,y);
				}
				break;
			case 2:
				currentDialogue=gp.npc[0].dialogue[10];
				g2.drawString(currentDialogue,x,y);
				break;
			case 3:
				if(gp.player.npcIndex==0)
					currentDialogue=gp.npc[0].dialogue[11];
				else if(gp.player.npcIndex==1)
					currentDialogue=gp.npc[1].dialogue[gp.npc[1].numberDialogue];
					if(currentDialogue!=null) {
						g2.drawString(currentDialogue,x,y);
					}
				break;
			}
	}
	public void addMessage(String text) {
		// TODO Auto-generated method stub
		
	}
	
	public void drawTitleScreen() {
		g2.setColor(new Color(70,120,80));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
	 	g2.setFont(g2.getFont().deriveFont(Font.BOLD,86F));
		String text= "LOST TO ISLAND";
		int x=getXforCenteredText(text);
		int y=gp.tileSize*3;
		g2.setColor(Color.white);
		g2.drawString(text,x,y);
		//g2.setColor(new (60,60,60));
	String start="START";	
	int x1=getXforCenteredText(start);
	int y1=gp.tileSize*5;
	g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
	g2.drawString(start, x1, y1);
	}
	
	public void drawPauseScreen()
	{
		String text="PAUSED";
		int x=gp.screenWidth/2-gp.tileSize*5/2;
		int y=gp.screenHeight/2-gp.tileSize*3;
		g2.drawImage(menu.image,x,y,gp.tileSize*5,gp.tileSize*6,null);
	}
	
	public void drawCharacterScreen() {
		final int frameX=gp.tileSize*2;
		final int frameY=gp.tileSize;
		final int frameWidth=gp.tileSize*5;
		final int frameHeight=gp.tileSize*10;
		drawSubWindow(frameX,frameY,frameWidth,frameHeight);
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
	}
	
	public void drawInventory() {
		// FRAME
		int frameX = gp.tileSize*9 ;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*6;
		int frameHeight = gp.tileSize*5 ;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// SLOT
		final int slotXstart = frameX + 20 ;
		final int slotYstart = frameY + 20 ;
		int slotX = slotXstart  ;
		int slotY = slotYstart  ;
		int slotSize = gp.tileSize + 3;
		
		//DRAW PLAYER'S ITEMS
		for(int i= 0;i< gp.player.inventory.size();i++) {
			g2.drawImage(gp.player.inventory.get(i).down1,slotX, slotY, null);
			slotX += slotSize;
					
			if(i==4 || i==9 || i==14) {
				slotX = slotXstart;
				slotY += slotSize;
			}
		}
		
		//CURSOR
		int cursorX = slotXstart + (slotSize * slotCol -2);
		int cursorY = slotXstart + (slotSize * slotRow -379);
		int cursorWidth= gp.tileSize  ;
		int cursorHeight= gp.tileSize ;
		
		//DRAW CURSOR
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
		
		// DESCRIPTION FRAME 
		int dFrameX = frameX;
		int dFrameY = frameY+ frameHeight;
		int dFrameWidth = frameWidth;
		int dFrameHeight = gp.tileSize*3;
		drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
		
		// DRAW DESCRIPTION TEXT
		int textX = dFrameX + 20;
		int textY = dFrameY + gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(28F));
		
		int itemIndex = getItemIndexOnSlot();
		if(itemIndex < gp.player.inventory.size()) {
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
	
	public int getXforCenteredText(String text) {
		int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
		int x=gp.screenWidth/2-length/2;
		return x;
	}
}
