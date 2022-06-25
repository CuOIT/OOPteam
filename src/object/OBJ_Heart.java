package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity{
	GamePanel gp;
	public OBJ_Heart(GamePanel gp) {
		super(gp);
		this.gp=gp;
		name="Heart";
		value = 2;
		down1=setup("/objects/hp_full",gp.tileSize,gp.tileSize);
		image=setup("/objects/hp_full",gp.tileSize,gp.tileSize);
		image2=setup("/objects/hp_half",gp.tileSize,gp.tileSize);
		image3=setup("/objects/hp_plank",gp.tileSize,gp.tileSize);
		
}
	public void use(Entity entity){
		gp.gameState = gp.dialogueState;
		entity.life += value;
}
}




