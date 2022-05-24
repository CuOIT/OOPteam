package main;

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
//		else if(gp.gameState==gp.dialogueState)
//		{	drawPlayerLife();
//			drawDialogueScreen();
//	}
 
// bo sung tam thoi NHHDANG
		else if(gp.gameState==gp.dialogueState){	
			drawPlayerLife();
			drawDialogueScreen();
		}
	}

	public void drawDialogueScreen(){
	
	}
	// het bo sung NHHDang
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
	
	public int getXforCenteredText(String text) {
		int length=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x=gp.screenWidth/2-length/2;
		return x;
	}
}
