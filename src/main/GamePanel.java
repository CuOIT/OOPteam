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
import object.Object;
import entity.Human;
import entity.Monster;
import entity.Player;
import entity.Projectile;
public class GamePanel extends JPanel implements Runnable {
//screen settings
	public final int ORIGINAL_TILE_SIZE = 16;//16x16 tile;
	public	final int SCALE=3;
	public final int TILE_SIZE=ORIGINAL_TILE_SIZE*SCALE;	//48 pixels
	public final int MAX_SCREEN_COL=25;
	public final int MAX_SCREEN_ROW=15;
	public final int SCREEN_WIDTH=TILE_SIZE * MAX_SCREEN_COL;//1200 pixels
	public final int SCREEN_HEIGHT=TILE_SIZE * MAX_SCREEN_ROW;//720 pixels
	
	public final int MAX_MAP=5;
	public final int MAX_WORLD_COL=50;
	public final int MAX_WORLD_ROW=50;
	public final int worldWidth=TILE_SIZE*MAX_WORLD_COL;
	public final int worldHeight=TILE_SIZE*MAX_WORLD_ROW;
	public int currentMap=0;
	public int frameCounter;
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
	public Object obj[][] = new Object[MAX_MAP][200];
	public Human npc[][]= new Human[MAX_MAP][10];
	public Monster monster[][]=new Monster[MAX_MAP][20];
	public ArrayList<Entity> entityList=new ArrayList<>();
	public ArrayList<Projectile> projectileList=new ArrayList<>();	
	
	//GAME STATE
	public int gameState;
	public final int TITLE_STATE=0;
	public final int DIFFICULT_STATE=1;
	public final int PLAY_STATE=2;
	public final int DIALOGUE_STATE=3;
	public final int GAME_OVER_STATE=4;
	public final int CHARACTER_STATE=5;
	public final int OPTION_STATE=6; 
	public final int SOUND_STATE1=7;
	public final int SOUND_STATE2=8;
	public final int VICTORY_STATE=9;
		
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
		playMusic(0);
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
		try {
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
		}catch(Exception e ) {
			System.out.println("Loi");
		}
	}
	public void update() {
		if(gameState==PLAY_STATE) 
			player.update();
		
		
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

		if(gameState==DIALOGUE_STATE) {
			ui.drawDialogueScreen(player.getNPCINdex());
		}
		if(gameState==GAME_OVER_STATE) {
			if(frameCounter==1) {
				stopMusic();
				playSE(13);
			}
			if(frameCounter<120)
				frameCounter++;
			ui.drawGameOverScreen();
			
		}
		if(gameState==VICTORY_STATE) {
			if(frameCounter==1) {
				stopMusic();
				playMusic(12);
			}
			if(frameCounter<120)
				frameCounter++;
			ui.drawVictoryScreen();
			
		}

		
		
	}
	public void drawToTempScreen() {
		//DEBUG
			
				if(gameState==TITLE_STATE) {
					ui.draw(g2);
				}
				else if(gameState==GAME_OVER_STATE) {
					ui.draw(g2);
					player.draw(g2);
				}
				else 
				{
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
								int y1=o1.worldY;
								int y2=o2.worldY;
								if(o1.name=="Pine_tree" || o1.name=="Tree") {
									y1+=TILE_SIZE+20;
								}
								if(o2.name=="Pine_tree" || o2.name=="Tree") {
									y2+=TILE_SIZE+20;
								}
								int result = Integer.compare(y1, y2);
								return result;
							}
						});
					
				for(int i=0;i<entityList.size();i++)
				{
						entityList.get(i).draw(g2);
				}
				entityList.clear();
				ui.draw(g2);
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
		screenWidth2=Main.window.getWidth();
		screenHeight2=Main.window.getHeight();
		
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