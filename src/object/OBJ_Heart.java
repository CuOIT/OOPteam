package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity{
	public OBJ_Heart(GamePanel gp) {
		super(gp);
		name="Heart";
		image=setup("/objects/hp_full");
		image2=setup("/objects/hp_half");
		image3=setup("/objects/hp_plank");
		
}
}




