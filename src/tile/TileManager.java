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
	public int mapTileNum[][][];
	public final int MAX_TILES = 200;
	public TileManager(GamePanel gp) {
	this.gp=gp;	
	tile=new Tile[MAX_TILES];
	
	mapTileNum= new int[gp.MAX_MAP][gp.MAX_WORLD_COL][gp.MAX_WORLD_ROW];
	getTileImage();
	loadMap("/map/skull_land.txt",0);
	loadMap("/map/cave.txt",1);
	}
	public void getTileImage() {
		setup(0,"water",true);
		setup(1,"sand",false);
		setup(2,"grass1",false);
		setup(3,"mountain",false);
		
		//Hoa
		setup(4,"rock",false);
		setup(5,"coral",false);
		setup(6,"dark.water",false);
		setup(7,"dark.water.right.under",false);
		setup(8,"dark.water.under",false);
		setup(9,"dark.water.right.side",false);
		setup(10,"mushroom",false);
		
		
		
		//Nui
		setup(11,"mountain.above",true);
		setup(12,"mountain.right.above",true);
		setup(13,"mountain.right.side",true);
		setup(15,"moutain.left.above",true);
		setup(16,"moutain.left.side",true);
		setup(17,"moutain.left.under",true);
		setup(18,"moutain.right.under",true);
		setup(19,"moutain.under",true);
		setup(14,"mountain.stair",false);
		
		//Ho
		setup(20,"lake",true);
		setup(21,"lake.above",true);
		setup(22,"lake.above.right",true);
		setup(23,"lake.left.above",true);
		setup(24,"lake.left.side",true);
		setup(25,"lake.left.under",true);
		setup(26,"lake.right.side",true);
		setup(27,"lake.right.under",true);
		setup(28,"lake.under",true);
		
		//Cat
		setup(29,"sand.above",false);
		setup(30,"sand.left.above",false);
		setup(31,"sand.left.side",false);
		setup(32,"sand.left.under",false);
		setup(33,"sand.right.above",false);
		setup(34,"sand.right.side",false);
		setup(35,"sand.right.under",false);
		setup(36,"sand.under",false);
		
		//Nuoc
		setup(37,"shore.above",false);
		setup(38,"shore.left.above",false);
		setup(39,"shore.left.side",false);
		setup(40,"shore.left.under",false);
		setup(41,"shore.right.above",false);
		setup(42,"shore.right.side",false);
		setup(43,"shore.right.under",false);
		setup(44,"shore.under",false);
		setup(45,"shore.right.above.corner",false);
		setup(46,"shore.right.under.corner",false);
		setup(47,"shore.left.above.corner",false);
		setup(48,"shore.left.under.corner",false);
        
		//Cay
		setup(49,"pine.tree",false);
		setup(50,"tree",false);
		setup(51,"flower1",false);
		setup(52,"flower2",false);
		setup(53,"flower3",false);
		setup(54,"sand.coral",false);
		setup(55,"sand.seashell",false);
		setup(56,"sand.shell",false);
		setup(57,"bush",false);
		
		//Cat-Co
		setup(58,"sand.corner1",false);
		setup(59,"sand.corner2",false);
		setup(70,"sand.corner3",false);
		setup(61,"sand.corner4",false);
		
		//Cave
		setup(62,"above",false);
		setup(63,"left.above.corner",false);
		setup(64,"right.above.corner",false);
		setup(65,"under",false);
		setup(66,"cave.stair",false);
		setup(67,"left.side",false);
		setup(68,"right.side",false);
		setup(69,"ground1",false);
		setup(60,"left.under.corner",false);
		setup(71,"right.under.corner",false);
		setup(72,"dark.water2",false);
		setup(73,"mountain2",false);
		setup(74,"black",true);
			setup(75,"ground2",false);
		
	}
	public void setup(int index,String imagePath,boolean collision) {
		try {
			tile[index]=new Tile();
			tile[index].image=ImageIO.read(getClass().getResourceAsStream("/tiles/"+imagePath+".png"));
			tile[index].image=UtilityTool.scaledImage(tile[index].image, gp.TILE_SIZE, gp.TILE_SIZE);
			tile[index].collision=collision;
		}
		catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	public void loadMap(String mapName, int mapNumber) {
		try {
			InputStream is=getClass().getResourceAsStream(mapName);
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			int col=0;
			int row=0;
			while(col<gp.MAX_WORLD_COL && row<gp.MAX_WORLD_COL) {
				String line=br.readLine();
				//System.out.println(gp.MAX_WORLD_COL+" "+gp.maxWorldRow);
				while(col < gp.MAX_WORLD_COL) {
					String numbers[]=line.split(" ");
					int num=Integer.parseInt(numbers[col]);
					mapTileNum[mapNumber][col][row]=num;
					col++;
				}
				if(col==gp.MAX_WORLD_COL) {
					col=0;
					row++;
				}
			}
			br.close();
		}catch(Exception e)
		{};
	}
	public void draw(Graphics2D g2) {
		//g2.drawImage(tile[0].image,0,0,gp.TILE_SIZE,gp.TILE_SIZE,null);
	//	g2.drawImage(tile[1].image,48,0,gp.TILE_SIZE,gp.TILE_SIZE,null);
		//g2.drawImage(tile[2].image,96,0,gp.TILE_SIZE,gp.TILE_SIZE,null);
		
		int worldCol=0;
		int worldRow=0;
		while(worldCol<gp.MAX_WORLD_COL && worldRow<gp.MAX_WORLD_ROW) {
			int tileNum=mapTileNum[gp.currentMap][worldCol][worldRow];			
			int worldX=worldCol*gp.TILE_SIZE;
			int worldY=worldRow*gp.TILE_SIZE;
			int screenX=worldX-gp.player.worldX+gp.player.screenX;
			int screenY=worldY-gp.player.worldY+gp.player.screenY;
			if(gp.player.screenX>gp.player.worldX) {
				screenX=worldX;
			}
			if(gp.player.screenY>gp.player.worldY) {
				screenY=worldY;
			}
			int rightOffset=gp.SCREEN_WIDTH-gp.player.screenX;
			 if(rightOffset>gp.worldWidth-gp.player.worldX) {
				 screenX=gp.SCREEN_WIDTH-(gp.worldWidth-worldX);
			 }
			int bottomOffset=gp.SCREEN_HEIGHT-gp.player.screenY;
				if(bottomOffset>gp.worldHeight-gp.player.worldY) {
					screenY=gp.SCREEN_HEIGHT- (gp.worldHeight-worldY);
			 }
			if(worldX+gp.TILE_SIZE>gp.player.worldX-gp.player.screenX && 
				worldX-gp.TILE_SIZE<gp.player.worldX+gp.player.screenX &&
				worldY+gp.TILE_SIZE>gp.player.worldY-gp.player.screenY &&
				worldY-gp.TILE_SIZE<gp.player.worldY+gp.player.screenY) {
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
		
			if(worldCol==gp.MAX_WORLD_COL) {
				worldCol=0;	
				worldRow++;
			
			}
		}
	}
}
