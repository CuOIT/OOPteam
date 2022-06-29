package object;


import entity.Entity;
import main.GamePanel;
public class OBJ_Entry_Cave extends Entity{
	public OBJ_Entry_Cave(GamePanel gp) {
		super(gp);
		name="Entry_Cave";
		collision=true;
		down1=setup("/objects/entry_cave",gp.tileSize,gp.tileSize);
	}

}
