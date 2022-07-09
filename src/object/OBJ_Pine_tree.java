package object;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pine_tree extends Entity {
	public OBJ_Pine_tree(GamePanel gp) {
		super(gp);
		name="Pine_tree";
		solidArea=new Rectangle(36,96,24,48);
		down1=setup("/objects/pine.tree", gp.TILE_SIZE*2, gp.TILE_SIZE*3);
}
}
