package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Hard_Shileld extends Entity {
	public OBJ_Hard_Shileld(GamePanel gp) {
		super(gp);
		name="Shileld Hard";
		down1=setup("/objects/hard_shileld",gp.tileSize,gp.tileSize);
		description = "[" + name + "]\nA Hard Shileld.";
	}
	
}