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
	public final int ORIGINAL_TILE_SIZE = 16;//16x16 tile;
	public	final int SCALE=3;
	public final int TILE_SIZE=ORIGINAL_TILE_SIZE*SCALE;	
	public final int MAX_SCREEN_COL=25;
	public final int MAX_SCREEN_ROW=15;
	public final int SCREEN_WIDTH=TILE_SIZE * MAX_SCREEN_COL;//768 pixels
	public final int SCREEN_HEIGHT=TILE_SIZE * MAX_SCREEN_ROW;//576 pixels
	
	public final int MAX_MAP=5;
	public final int MAX_WORLD_COL=50;
	public final int MAX_WORLD_ROW=50;
	public final int worldWidth=TILE_SIZE*MAX_WORLD_COL;
	public final int worldHeight=TILE_SIZE*MAX_WORLD_ROW;
	public int currentMap=0;
	int screenWidth2=SCREEN_WIDTH;
	int screenHeight2=SCREEN_HEIGHT;
	
	//Graphics
	BufferedImage tempScreen;
	Graphics2D g2;
	
	//Sound
	Sound music=new Sound();	
	Sound se=new Sound();
	
	//FPS
	public final int FPS=60;
	
	//Manager
	TileManager tileM=new TileManager(this);
	public KeyHandler keyH=new KeyHandler(this);	
	public UI ui=new UI(this);
	public CollisionChecker cChecker=new CollisionChecker(this);
	public AssetSetter aSetter=new AssetSetter(this);
	
	//Thread
	Thread gameThread;

	//entity and object
	public Player player=new Player(this,keyH);
	public Entity obj[][] = new Entity[MAX_MAP][200];
	public Entity npc[][]= new Entity[MAX_MAP][10];
	public Entity monster[][]=new Entity[MAX_MAP][20];
	public ArrayList<Entity> entityList=new ArrayList<>();
	public ArrayList<Entity> projectileList=new ArrayList<>();	
	
	//GAME STATE
	public int gameState;
	public final int TITLE_STATE=0;
	public final int PLAY_STATE=1;
	public final int PAUSE_STATE=2;
	public final int DIALOGUE_STATE=3;
	public final int CHARACTER_STATE=5;
	public final int OPTION_STATE=6; 
	public final int gameOverState=7;
	
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
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
		gameState=TITLE_STATE;
		
		tempScreen=new BufferedImage(SCREEN_WIDTH,SCREEN_HEIGHT,BufferedImage.TYPE_INT_ARGB);
		g2=(Graphics2D)tempScreen.getGraphics();
		
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
			System.exit(1);
	}
	public void update() {
		if(gameState==PLAY_STATE) 
			player.update();
	//	if(player.life<=0) gameState=gameOverState;
		for(int i=0;i<npc[0].length;i++)
			if(npc[currentMap][i]!=null)	
				npc[currentMap][i].update();
		for(int i=0;i<monster[0].length;i++){
			if(monster[currentMap][i]!=null){
				if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false){
					monster[currentMap][i].update();
				}	
				if(monster[currentMap][i].alive == false){
					monster[currentMap][i].checkDrop();
					monster[currentMap][i] = null;
				}
			}
		}
		for(int i=0;i<projectileList.size();i++){
			if(projectileList.get(i)!=null){
				if(projectileList.get(i).alive == true ){
					projectileList.get(i).update();
				}	
				if(projectileList.get(i).alive == false){
					projectileList.remove(i);
				}
			}
		}
	
		if(gameState==PAUSE_STATE) {
		}
//		if(gameState==loadState) {
//			
//		}
//		if(gameState==CHARACTER_STATE) {
//			ui.drawCharacterScreen();
//		}
		if(gameState==DIALOGUE_STATE) {
			ui.drawDialogueScreen(player.npcIndex);
		}
		if(gameState==gameOverState) {
			
			
		}
		
	}
	public void drawToTempScreen() {
		//DEBUG
				long drawStart = 0;
				if(keyH.checkDrawTime==true) {
					drawStart=System.nanoTime();
				}
				
				if(gameState==TITLE_STATE) {
					ui.draw(g2);
			
				}
				else 
				{
					//TILE
//					
					tileM.draw(g2);
					
					entityList.add(player);
					for(int i=0;i<npc[0].length;i++) {
						if(npc[currentMap][i]!=null)
						{
							entityList.add(npc[currentMap][i]);
						}
					}
						for(int i=0;i<obj[0].length;i++) {
							if(obj[currentMap][i]!=null)
							{
								entityList.add(obj[currentMap][i]);
							}
					}
						for(int i=0;i<monster[0].length;i++) {
							if(monster[currentMap][i]!=null)
							{
								entityList.add(monster[currentMap][i]);	
							}
						}
						for(int i=0;i<projectileList.size();i++) {
							if(projectileList.get(i) != null)
							{
								entityList.add(projectileList.get(i));	
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
//				if(gameState==CHARACTER_STATE) {
//					ui.drawCharacterScreen();
//				}
			}
				
				
	}
	public void drawToScreen() {
		Graphics g=getGraphics();
		g.drawImage(tempScreen,0,0,screenWidth2,screenHeight2,null);
		g.dispose();
	}
	public void setFullScreen() {
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd=ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.window);
		System.out.println("AAA");
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