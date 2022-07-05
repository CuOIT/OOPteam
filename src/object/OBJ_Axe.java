package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {

	public OBJ_Axe(GamePanel gp) {
		super(gp);
		name = "Axe";
		down1=setup("/objects/axe",gp.TILE_SIZE,gp.TILE_SIZE);
		description = "[" + name + "]\nIt is Axe.";
	}

}
