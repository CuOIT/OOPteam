package object;


import entity.Entity;
import main.GamePanel;
public class OBJ_Entry_Cave extends Object{
	public OBJ_Entry_Cave(GamePanel gp) {
		super(gp);
		name="Entry_Cave";
		down1=setup("/objects/entry_cave",gp.TILE_SIZE,gp.TILE_SIZE);
	}

}
