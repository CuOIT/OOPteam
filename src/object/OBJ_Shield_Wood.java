package object;



import entity.Entity;
import main.GamePanel;
public class OBJ_Shield_Wood extends Entity{
	public OBJ_Shield_Wood(GamePanel gp) {
	super(gp);
	name="Shield_Wood";
	down1=setup("/objects/Shield_Wood",gp.tileSize,gp.tileSize);
	description = "[" + name + "]\nMade by wood.";
}
}

