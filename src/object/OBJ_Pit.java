package object;

import entity.Entity;
import main.GamePanel;
public class OBJ_Pit extends Entity{

	public OBJ_Pit(GamePanel gp) {
	super(gp);
	name="Pit";
	collision=true;
	down1=setup("/objects/fire_pit", gp.TILE_SIZE, gp.TILE_SIZE);
}
}
