package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {

	public OBJ_Axe(GamePanel gp) {
		super(gp);
		name = "Axe";
		down1=setup("/objects/axe",gp.tileSize,gp.tileSize);
		description = "[" + name + "]\nIt is Axe.";
	}

}
