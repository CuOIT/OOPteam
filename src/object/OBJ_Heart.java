package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Object{
	public OBJ_Heart(GamePanel gp) {
		super(gp);
		name="Heart";
		down1=setup("/objects/hp_full", gp.TILE_SIZE, gp.TILE_SIZE);

}

}




