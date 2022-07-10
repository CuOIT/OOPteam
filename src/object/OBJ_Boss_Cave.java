package object;

import java.awt.Rectangle;

import main.GamePanel;
import object.Object;
public class OBJ_Boss_Cave extends Object{

	public OBJ_Boss_Cave(GamePanel gp)
	{
		super(gp);
		name="Boss_Cave";
		solidArea=new Rectangle(0,0,gp.TILE_SIZE*3,gp.TILE_SIZE);
	}
}
