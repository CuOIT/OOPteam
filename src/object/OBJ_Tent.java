package object;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;
public class OBJ_Tent extends Entity{

	public OBJ_Tent(GamePanel gp) {
	super(gp);
	name="Tent";
	collision=true;
	solidArea=new Rectangle(0,0,96,96);
	down1=setup("/objects/tent", gp.tileSize*2, gp.tileSize*2);
}
}