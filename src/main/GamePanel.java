package main;
import tile.TileManager;
import main.CollisionChecker;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Component;
import entity.Entity;
import entity.Player;
public class GamePanel extends JPanel implements Runnable {
//screen settings
	public final int originalTileSize = 16;//16x16 tile;
	public	final int scale=3;
	public final int tileSize=originalTileSize*scale;	
	public final int maxScreenCol=30;
	public final int maxScreenRow=20;
	public final int screenWidth=tileSize * maxScreenCol;//768 pixels
	public final int screenHeight=tileSize * maxScreenRow;//576 pixels
	
	public final int maxWorldCol=50;
	public final int maxWorldRow=50;
	public final int worldWidth=tileSize*maxWorldCol;
	public final int worldHeight=tileSize*maxWorldRow;
	
	int screenWidth2=screenWidth;
	int screenHeight2=screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	//FPS
	int FPS=60;
	
	//SYSTEM
	TileManager tileM=new TileManager(this);
	
	public KeyHandler keyH=new KeyHandler(this);	
	  
	Sound music=new Sound();	
	Sound se=new Sound();
	public UI ui=new UI(this);
	
	public CollisionChecker cChecker=new CollisionChecker(this);
	public AssetSetter aSetter=new AssetSetter(this);
	Thread gameThread;
	
	//entity and object
	public Player player=new Player(this,keyH);
	public Entity obj[] = new Entity[50];
	public Entity npc[]= new Entity[10];
	public Entity monster[]=new Entity[20];
	public ArrayList<Entity> entityList=new ArrayList<>();
	//GAME STATE
	public int gameState;
	public final int titleState=0;
	public final int playState=1;
	public final int pauseState=2;
	public final int loadState=3;
	public final int dialogueState=4;
	public final int characterState=5;
	
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
		aSetter.setNPC();
		aSetter.setMonster();
		//playMusic(0); 
		//stopMusic();
		gameState=titleState;
		
		tempScreen=new BufferedImage(screenWidth,screenHeight,BufferedImage.TYPE_INT_ARGB);
		g2=(Graphics2D)tempScreen.getGraphics();
		//setFullScreen();
		}
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	
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
			//repaint();
			drawToTempScreen();
			drawToScreen();
			delta--;
			drawCount++;
			}
			if(timer>=1000000000)
			{   
				drawCount=0;
				timer=0;
			}
		}
	}
	public void update() {
		if(gameState==playState)
		player.update();
		for(int i=0;i<npc.length;i++)
			if(npc[i]!=null)	
				npc[i].update();
		for(int i=0;i<monster.length;i++){
			if(monster[i]!=null){
				if(monster[i].alive == true && monster[i].dying == false){
					monster[i].update();
				}	
				if(monster[i].alive == false){
					monster[i] = null;
				}
			}
		}
		if(gameState==pauseState) {
		}
		if(gameState==loadState) {
			tileM.loadMap("/map/test.txt");
		}
		if(gameState==characterState) {
			ui.drawCharacterScreen();
		}

	}
	public void drawToTempScreen() {
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
//					
					tileM.draw(g2);
					
					entityList.add(player);
					for(int i=0;i<npc.length;i++) {
						if(npc[i]!=null)
						{
							entityList.add(npc[i]);
						}
					}
						for(int i=0;i<obj.length;i++) {
							if(obj[i]!=null)
							{
								entityList.add(obj[i]);
							}
					}
						for(int i=0;i<monster.length;i++) {
							if(monster[i]!=null)
							{
								entityList.add(monster[i]);	
							}
						}
						Collections.sort(entityList,new Comparator<Entity>() {

							@Override
							public int compare(Entity o1, Entity o2) {
								int result = Integer.compare(o1.worldY, o2.worldY);
								return result;
							}
						});
					
				for(int i=0;i<entityList.size();i++)
				{
						entityList.get(i).draw(g2);
				}
				entityList.clear();
				ui.draw(g2);
				if(gameState==characterState) {
					ui.drawCharacterScreen();
				}
			}
				
				
	}
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D g2=(Graphics2D)g;
//	
//		
//		//DEBUG
//		long drawStart = 0;
//		if(keyH.checkDrawTime==true) {
//			drawStart=System.nanoTime();
//		}
//		
//		if(gameState==titleState) {
//			ui.draw(g2);
//		}
//		else 
//		{
//			//TILE
////			
//			tileM.draw(g2);
//			
//			entityList.add(player);
//			for(int i=0;i<npc.length;i++) {
//				if(npc[i]!=null)
//				{
//					entityList.add(npc[i]);
//				}
//			}
//				for(int i=0;i<obj.length;i++) {
//					if(obj[i]!=null)
//					{
//						entityList.add(obj[i]);
//					}
//			}
//				Collections.sort(entityList,new Comparator<Entity>() {
//
//					@Override
//					public int compare(Entity o1, Entity o2) {
//						int result = Integer.compare(o1.worldY, o2.worldY);
//						return result;
//					}
//				});
//			
//		for(int i=0;i<entityList.size();i++)
//		{
//				entityList.get(i).draw(g2);
//		}
//		for(int i=0;i<entityList.size();i++)
//		{
//				entityList.remove(i);
//		}
//		ui.draw(g2);
//		if(gameState==characterState) {
//			ui.drawCharacterScreen();
//		}
//	}
//		
//		
//
//		g2.dispose();
//		
//	}
	public void drawToScreen() {
		Graphics g=getGraphics();
		g.drawImage(tempScreen,0,0,screenWidth2,screenHeight2,null);
		g.dispose();
	}
	public void setFullScreen() {
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd=ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.window);
		//GET FULL SCREEN WIDTH AND HEIGHT
		screenWidth2=Main.window.getWidth();
		screenHeight2=Main.window.getHeight();
		
	}
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
		System.out.println("dsa");
	}
	public void stopMusic() {
		music.stop();
	}
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
}