package main;
import tile.TileManager;
import main.CollisionChecker;
import object.SuperObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Component;
import entity.Player;
public class GamePanel extends JPanel implements Runnable {
//screen settings
	public final int originalTileSize = 16;//16x16 tile;
	public	final int scale=3;
	public final int tileSize=originalTileSize*scale;	
	public final int maxScreenCol=16;
	public final int maxScreenRow=12;
	public final int screenWidth=tileSize * maxScreenCol;//768 pixels
	public final int screenHeight=tileSize * maxScreenRow;//576 pixels
	
	public final int maxWorldCol=50;
	public final int maxWorldRow=50;
	public final int worldWidth=tileSize*maxWorldCol;
	public final int worldHeight=tileSize*maxWorldRow;
	
 
	//FPS
	int FPS=60;
	
	//SYSTEM
	TileManager tileM=new TileManager(this);
	
	KeyHandler keyH=new KeyHandler(this);	
	  
	Sound music=new Sound();
	Sound se=new Sound();
	public UI ui=new UI(this);
	
	public CollisionChecker cChecker=new CollisionChecker(this);
	public AssetSetter aSetter=new AssetSetter(this);
	Thread gameThread;
	
	//entity and object
	public Player player=new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[10];
	
	
	//GAME STATE
	public int gameState;
	public final int titleState=0;
	public final int playState=1;
	public final int pauseState=2;
	
	
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		//set size of this class
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	public void setUpGame() {
		aSetter.setObject();
		//playMusic(0); 
		//stopMusic();
		gameState=titleState;
	}
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
/*	public void run() {
		double drawInterval=1000000000/FPS;
		double nextDrawTime=System.nanoTime()+drawInterval;
		int i=0;
		while(gameThread != null) {
			if(i%60==0)
			System.out.print(playerX+" "+playerY);
			i++;
			//1 UPDATE: character
			update();
			//2 DRAW ENVIROMENT
			repaint();
			try {
			double remainingTime=nextDrawTime-System.nanoTime();
			remainingTime=remainingTime/1000000;
			
			if(remainingTime<0) {
				remainingTime=0;
			}
			Thread.sleep((long)remainingTime);
			
			nextDrawTime+= drawInterval;
			
				} catch(InterruptedException e){
				e.printStackTrace();
				
			}
		}
		
	}*/
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime=System.nanoTime();
		long currentTime;
		long timer=0;
		int drawCount=0;
		while(gameThread!=null) {
			currentTime =System.nanoTime();
			delta+=(currentTime-lastTime)/drawInterval;
			timer+=(currentTime-lastTime);
			lastTime=currentTime;
			if(delta>=1) {
			update();
			repaint();
			delta--;
			drawCount++;
			}
			if(timer>=1000000000)
			{
			//	System.out.println("FPS: "+drawCount);
				drawCount=0;
				timer=0;
			}
		}
	}
	public void update() {
		if(gameState==playState)
		player.update();
		if(gameState==pauseState) {
		}
			//

	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
	
		
		//DEBUG
		long drawStart = 0;
		if(keyH.checkDrawTime==true) {
			drawStart=System.nanoTime();
		}
		
		if(gameState==titleState) {
			ui.draw(g2);
		}
		else 
		{
			//TILE
			tileM.draw(g2);
			
			for(int i=0;i<obj.length;i++)
			{
				if(obj[i]!=null)
					obj[i].draw(g2,this);
		
			}
			//Player
			player.draw(g2);
			//UI
			ui.draw(g2);
		}
		//TILE
//		tileM.draw(g2);
//		
//		for(int i=0;i<obj.length;i++)
//		{
//			if(obj[i]!=null)
//				obj[i].draw(g2,this);
//	
//		}
//		//Player
//		player.draw(g2);
//		//UI
//		ui.draw(g2);
//		
		g2.dispose();
		
	}
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic() {
		music.stop();
	}
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
}