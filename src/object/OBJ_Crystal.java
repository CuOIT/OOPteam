package object;
import main.GamePanel;
import object.Object;
public class OBJ_Crystal extends Object{

	public OBJ_Crystal(GamePanel gp)
	{
		super(gp);
		name="Crystal";
		collision=false;
		down1=setup("/objects/hp_plank",gp.TILE_SIZE,gp.TILE_SIZE);
	}
}
