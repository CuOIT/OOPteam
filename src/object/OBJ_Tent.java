package object;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;
public class OBJ_Tent extends Object{

	public OBJ_Tent(GamePanel gp) {
	super(gp);
	name="Tent";
	collision=true;
	solidArea=new Rectangle(32,0,32,64);
	down1=setup("/objects/tent", gp.TILE_SIZE*2, gp.TILE_SIZE*2);
}
}
