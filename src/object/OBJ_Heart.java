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
		down1=setup("/objects/hp_full",gp.TILE_SIZE,gp.TILE_SIZE);
		image=setup("/objects/hp_full",gp.TILE_SIZE,gp.TILE_SIZE);
		image2=setup("/objects/hp_half",gp.TILE_SIZE,gp.TILE_SIZE);
		image3=setup("/objects/hp_plank",gp.TILE_SIZE,gp.TILE_SIZE);
		
}
	public void use(Entity entity){
		gp.gameState = gp.dialogueState;
		entity.life += value;
}
}




