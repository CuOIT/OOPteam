package tile;
import main.GamePanel;
import main.UtilityTool;

import java.io.BufferedReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.InputStreamReader;
public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
	this.gp=gp;
	tile=new Tile[10];
	mapTileNum= new int[gp.maxWorldCol][gp.maxWorldRow];
	getTileImage();
	loadMap("/map/map01.txt");
	}
	public void getTileImage() {
		setup(0,"water",true);
		setup(1,"beach_above_sand",false);
		setup(2,"beach_sand_Rightcorner",false);
		setup(3,"beach_sand_right",false);
		setup(4,"beach_sand_leftunder",false);
		setup(5,"beach_sand_left",false);
		setup(6,"grass",false);
		setup(7,"tree_sand",true);
		setup(8,"Pine_tree",true);
		setup(9,"sand",false);
		setup(10,"beach_sand_Leftcorner",false);
		setup(12,"earth",false);
		
		
		
		
	}
	public void setup(int index,String imagePath,boolean collision) {
		UtilityTool uTool=new UtilityTool();
		try {
			tile[index]=new Tile();
			tile[index].image=ImageIO.read(getClass().getResourceAsStream("/tiles/"+imagePath+".png"));
			tile[index].image=uTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision=collision;
		}
		catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	public void loadMap(String s) {
		try {
			InputStream is=getClass().getResourceAsStream(s);
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			int col=0;
			int row=0;
			while(col<gp.maxWorldCol && row<gp.maxWorldRow) {
				String line=br.readLine();
				//System.out.println(gp.maxWorldCol+" "+gp.maxWorldRow);
				while(col < gp.maxWorldCol) {
					String numbers[]=line.split(" ");
					int num=Integer.parseInt(numbers[col]);
					mapTileNum[col][row]=num;
					col++;
				}
				if(col==gp.maxWorldCol) {
					col=0;
					row++;
				}
			}
			br.close();
		}catch(Exception e)
		{};
	}
	public void draw(Graphics2D g2) {
		//g2.drawImage(tile[0].image,0,0,gp.tileSize,gp.tileSize,null);
	//	g2.drawImage(tile[1].image,48,0,gp.tileSize,gp.tileSize,null);
		//g2.drawImage(tile[2].image,96,0,gp.tileSize,gp.tileSize,null);
		
		int worldCol=0;
		int worldRow=0;
		while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow) {
			int tileNum=mapTileNum[worldCol][worldRow];			
			int worldX=worldCol*gp.tileSize;
			int worldY=worldRow*gp.tileSize;
			int screenX=worldX-gp.player.worldX+gp.player.screenX;
			int screenY=worldY-gp.player.worldY+gp.player.screenY;
			if(gp.player.screenX>gp.player.worldX) {
				screenX=worldX;
			}
			if(gp.player.screenY>gp.player.worldY) {
				screenY=worldY;
			}
			int rightOffset=gp.screenWidth-gp.player.screenX;
			 if(rightOffset>gp.worldWidth-gp.player.worldX) {
				 screenX=gp.screenWidth-(gp.worldWidth-worldX);
			 }
			int bottomOffset=gp.screenHeight-gp.player.screenY;
				if(bottomOffset>gp.worldHeight-gp.player.worldY) {
					screenY=gp.screenHeight- (gp.worldHeight-worldY);
			 }
			if(worldX+gp.tileSize>gp.player.worldX-gp.player.screenX && 
				worldX-gp.tileSize<gp.player.worldX+gp.player.screenX &&
				worldY+gp.tileSize>gp.player.worldY-gp.player.screenY &&
				worldY-gp.tileSize<gp.player.worldY+gp.player.screenY) {
			g2.drawImage(tile[tileNum].image,screenX,screenY,null);
			}
			else if(gp.player.screenX>gp.player.worldX ||
					gp.player.screenY>gp.player.worldY||
					rightOffset>gp.worldWidth-gp.player.worldX||
					bottomOffset>gp.worldHeight-gp.player.worldY)
			{
				g2.drawImage(tile[tileNum].image,screenX,screenY,null);
			}
			worldCol++;
		
			if(worldCol==gp.maxWorldCol) {
				worldCol=0;	
				worldRow++;
			
			}
		}
	}
}
