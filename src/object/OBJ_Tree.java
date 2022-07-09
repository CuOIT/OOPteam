package object;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tree extends Entity{
	public OBJ_Tree(GamePanel gp) {
		super(gp);
		name="Tree";
		solidArea=new Rectangle(36,96,24,48);
		down1=setup("/objects/tree", gp.TILE_SIZE*2, gp.TILE_SIZE*3);
}
}
