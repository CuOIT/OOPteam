package object;



import entity.Entity;
import main.GamePanel;
public class OBJ_Sword_Normal extends Entity{
	public OBJ_Sword_Normal(GamePanel gp) {
	super(gp);
	name="Sword_Normal";
	down1=setup("/objects/sword_normal",gp.TILE_SIZE,gp.TILE_SIZE);
	description = "[" + name + "]\nAn old sword.";
	knockBackPower = 10;
}
}

